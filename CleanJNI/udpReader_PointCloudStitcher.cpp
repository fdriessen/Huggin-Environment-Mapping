#include <jni.h>
#include <iostream>
#include <string>
#include <sstream>
//#include <pcl/io/pcd_io.h>
//#include <pcl/point_types.h>
//#include <pcl/registration/icp.h>

#include <boost/make_shared.hpp>
#include <pcl/point_types.h>
#include <pcl/point_cloud.h>
#include <pcl/point_representation.h>

#include <pcl/io/pcd_io.h>

#include <pcl/filters/voxel_grid.h>

#include <pcl/filters/filter.h>

#include <pcl/features/normal_3d.h>

#include <pcl/registration/icp.h>
#include <pcl/registration/icp_nl.h>
#include <pcl/registration/transforms.h>

#include <pcl/segmentation/segment_differences.h>

#include "udpReader_PointCloudStitcher.h"

//convenient typedefs
typedef pcl::PointXYZ PointT;
typedef pcl::PointCloud<PointT> PointCloud;
typedef pcl::PointNormal PointNormalT;
typedef pcl::PointCloud<PointNormalT> PointCloudWithNormals;

pcl::PointCloud<pcl::PointXYZ>::Ptr cloud_in (new pcl::PointCloud<pcl::PointXYZ>);
pcl::PointCloud<pcl::PointXYZ>::Ptr cloud_out (new pcl::PointCloud<pcl::PointXYZ>);
pcl::PointCloud<pcl::PointXYZ>::Ptr cloud_test (new pcl::PointCloud<pcl::PointXYZ>);

int w= 640;
int h=480;
int skip=4;
bool newData = false;
int counter = 0;

int main() {  
  return 0; 
}

pcl::PointCloud<pcl::PointXYZ>::Ptr pairAlign (const pcl::PointCloud<pcl::PointXYZ>::Ptr cloud_src, const pcl::PointCloud<pcl::PointXYZ>::Ptr cloud_tgt, Eigen::Matrix4f &final_transform, bool downsample = false)
{
	//
	// Downsample for consistency and speed
	// \note enable this for large datasets
	pcl::PointCloud<pcl::PointXYZ>::Ptr src (new pcl::PointCloud<pcl::PointXYZ>);
	pcl::PointCloud<pcl::PointXYZ>::Ptr tgt (new pcl::PointCloud<pcl::PointXYZ>);
	pcl::VoxelGrid<PointT> grid;
	if (downsample)
	{
		grid.setLeafSize (0.05, 0.05, 0.05);
		grid.setInputCloud (cloud_src);
		grid.filter (*src);

		grid.setInputCloud (cloud_tgt);
		grid.filter (*tgt);
	}
	else
	{
		src = cloud_src;
		tgt = cloud_tgt;
	}

	// Compute surface normals and curvature
	PointCloudWithNormals::Ptr points_with_normals_src (new PointCloudWithNormals);
	PointCloudWithNormals::Ptr points_with_normals_tgt (new PointCloudWithNormals);

	pcl::NormalEstimation<PointT, PointNormalT> norm_est;
	pcl::search::KdTree<pcl::PointXYZ>::Ptr tree (new pcl::search::KdTree<pcl::PointXYZ> ());
	norm_est.setSearchMethod (tree);
	norm_est.setKSearch (30);
	
	norm_est.setInputCloud (src);
	norm_est.compute (*points_with_normals_src);
	pcl::copyPointCloud (*src, *points_with_normals_src);

	norm_est.setInputCloud (tgt);
	norm_est.compute (*points_with_normals_tgt);
	pcl::copyPointCloud (*tgt, *points_with_normals_tgt);

	//
	// Align
	pcl::IterativeClosestPointNonLinear<PointNormalT, PointNormalT> reg;
	reg.setTransformationEpsilon (1e-6);
	// Set the maximum distance between two correspondences (src<->tgt) to 10cm
	// Note: adjust this based on the size of your datasets
	reg.setMaxCorrespondenceDistance (0.1);  

	reg.setInputCloud (points_with_normals_src);
	reg.setInputTarget (points_with_normals_tgt);



	//
	// Run the same optimization in a loop and visualize the results
	Eigen::Matrix4f Ti = Eigen::Matrix4f::Identity (), prev, targetToSource;
	PointCloudWithNormals::Ptr reg_result = points_with_normals_src;
	reg.setMaximumIterations (3);
	for (int i = 0; i < 30; ++i)
	{
		// save cloud for visualization purpose
		points_with_normals_src = reg_result;

		// Estimate
		reg.setInputCloud (points_with_normals_src);
		reg.align (*reg_result);

		//accumulate transformation between each Iteration
		Ti = reg.getFinalTransformation () * Ti;

		//if the difference between this transformation and the previous one
		//is smaller than the threshold, refine the process by reducing
		//the maximal correspondence distance
		if (fabs ((reg.getLastIncrementalTransformation () - prev).sum ()) < reg.getTransformationEpsilon ())
		{
			reg.setMaxCorrespondenceDistance (reg.getMaxCorrespondenceDistance () - 0.001);
		}
		
		prev = reg.getLastIncrementalTransformation ();
	}

	//
	// Get the transformation from target to source
	targetToSource = Ti.inverse();

	pcl::PointCloud<pcl::PointXYZ>::Ptr output (new pcl::PointCloud<pcl::PointXYZ>);
	//
	// Transform target back in source frame
	pcl::transformPointCloud (*cloud_src, *output, targetToSource);

	//
	//Caculated difference between source and target
	pcl::PointCloud<pcl::PointXYZ> difference;
	pcl::SegmentDifferences<pcl::PointXYZ> dif;
	dif.setInputCloud (output);
	dif.setTargetCloud (cloud_tgt);
	dif.setDistanceThreshold (0.085);
	dif.segment(difference);

	pcl::PointCloud<pcl::PointXYZ> resultLocal;

	resultLocal += difference;
	resultLocal += *cloud_tgt;

	pcl::PointCloud<pcl::PointXYZ>::Ptr result(&resultLocal);

	//PCL_INFO("er zijn (%d) punten", diffOr->points.size());
	//*output = *diffOr;

	//PCL_INFO ("Size result(%d),tgt(%d),output(%d).\n", reg_result->points.size(),cloud_tgt->points.size(),output->points.size());

	std::stringstream ss;
	ss << counter;
	std::string s(ss.str());

	//pcl::io::savePCDFileASCII ("dataOut"+s+".pcd", *output);

	counter++;

	final_transform = targetToSource;

	return result;
 }

float rawDepthToMeters(int depthValue) {
	if (depthValue < 2047) {
		return (float)(1.0 / ((double)(depthValue) * -0.0030711016 + 3.3309495161));
	}
	return 0.0f;
}

JNIEXPORT jobjectArray JNICALL Java_udpReader_PointCloudStitcher_getPoints(JNIEnv *env, jobject) {
	jclass java_cls = (env)->FindClass("udpReader/Point");
	jvalue args[3];
	jobject object;

	jmethodID constructor = (env)->GetMethodID(java_cls, "<init>", "(FFF)V");
	
	jobjectArray resPionts = (jobjectArray) (env)->NewObjectArray(cloud_test->points.size(), java_cls, NULL);

	for(int i = 0; i < cloud_test->points.size(); ++i)
	{       
	        args[0].f = cloud_test->points[i].x; //x
	    	args[1].f = cloud_test->points[i].y; //y
	    	args[2].f = cloud_test->points[i].z; //z
	
	    object = (env)->NewObjectA(java_cls, constructor, args);
	    env->SetObjectArrayElement(resPionts, i, object);
	}
	return resPionts;
}

JNIEXPORT jobjectArray JNICALL Java_udpReader_PointCloudStitcher_getInputPoints(JNIEnv *env, jobject) {
	jclass java_cls = (env)->FindClass("udpReader/Point");
	jvalue args[3];
	jobject object;

	jmethodID constructor = (env)->GetMethodID(java_cls, "<init>", "(FFF)V");
	
	jobjectArray resPionts = (jobjectArray) (env)->NewObjectArray(cloud_in->points.size(), java_cls, NULL);

	for(int i = 0; i < cloud_in->points.size(); ++i)
	{       
	        args[0].f = cloud_in->points[i].x; //x
	    	args[1].f = cloud_in->points[i].y; //y
	    	args[2].f = cloud_in->points[i].z; //z
	
	    object = (env)->NewObjectA(java_cls, constructor, args);
	    env->SetObjectArrayElement(resPionts, i, object);
	}
	return resPionts;
}

JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_saveFrameFile(JNIEnv *, jobject, jint nr) {
	std::stringstream ss;
	ss << nr;
	std::string s(ss.str());

	pcl::io::savePCDFileASCII ("data"+s+".pcd", *cloud_in);

	return;
}

JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_saveOutputCloud(JNIEnv *, jobject) {
	pcl::io::savePCDFileASCII ("dataOutputCloud.pcd", *cloud_out);

	return;
}

JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_loadOutputCloud(JNIEnv *, jobject) {
	if (pcl::io::loadPCDFile<pcl::PointXYZ> ("dataOutputCloud.pcd", *cloud_out) == -1) //* load the file
  	{
  		PCL_ERROR ("kapot \n");
  	}
	
	return;
}

JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_resetOutputCloud(JNIEnv *, jobject) {
	pcl::PointCloud<pcl::PointXYZ>::Ptr empty (new pcl::PointCloud<pcl::PointXYZ>);
	*cloud_out = *empty;
	
	return;
}

JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_setInputOutput(JNIEnv *, jobject) {
	*cloud_out = *cloud_in;
	
	return;
}

/*JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_icp(JNIEnv *, jobject) {
	if(cloud_out->points.size() == 0) {
		*cloud_out = *cloud_in;
	} else {
		if(newData) {
			//pcl::PointCloud<pcl::PointXYZ>::Ptr result (new pcl::PointCloud<pcl::PointXYZ>), source, target;
  			Eigen::Matrix4f GlobalTransform = Eigen::Matrix4f::Identity (), pairTransform;
	
  			//source = cloud_out;
   			//target = cloud_in;

   			//source = cloud_in;
   			//target = cloud_out;
	
   			pcl::PointCloud<pcl::PointXYZ>::Ptr temp (new pcl::PointCloud<pcl::PointXYZ>);
   			temp = pairAlign (cloud_in, cloud_out, pairTransform, true);
   			
   			PCL_INFO("er zijn (%d) punten", temp->points.size());

   			//transform current pair into the global transform
    		//pcl::transformPointCloud (*temp, *result, GlobalTransform);
	
    		//update the global transform
    		GlobalTransform = pairTransform * GlobalTransform;
	
    		*cloud_out += *temp;

    		PCL_INFO("er zijn (%d) punten", cloud_out->points.size());
    		newData = false;
    	}
	}
  return;
} */

/*JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_icp(JNIEnv *, jobject) {
  	if(cloud_out->points.size() == 0) {
		*cloud_out = *cloud_in;
	} else {
		if(newData) {
			Eigen::Matrix4f GlobalTransform = Eigen::Matrix4f::Identity (), pairTransform;
			cloud_out = pairAlign (cloud_in, cloud_out, pairTransform, true);
			GlobalTransform = pairTransform * GlobalTransform;
		}
	}
  }*/

JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_icp(JNIEnv *, jobject) {
	if(cloud_out->points.size() == 0) {
		*cloud_out = *cloud_in;
		*cloud_test = *cloud_in;
	} else {
		if(newData) {
			pcl::PointCloud<pcl::PointXYZ>::Ptr local_in = cloud_in;
			pcl::PointCloud<pcl::PointXYZ>::Ptr local_out = cloud_out;

			//*local_in = *cloud_in;
			//*local_out = *cloud_out;

			pcl::PointCloud<pcl::PointXYZ>::Ptr src = local_in;
			pcl::PointCloud<pcl::PointXYZ>::Ptr tgt = local_out;
			/*pcl::VoxelGrid<PointT> grid;
			grid.setLeafSize (0.05, 0.05, 0.05);
			grid.setInputCloud (local_in);
			grid.filter (*src);

			grid.setInputCloud (local_out);
			grid.filter (*tgt);*/

			//*src = *local_in;
  			//*tgt = *local_out;

			pcl::IterativeClosestPointNonLinear<pcl::PointXYZ, pcl::PointXYZ> reg;
			
			//Termination: The epsilon (difference) between the previous transformation and the current estimated transformation is smaller than an user imposed value
			reg.setTransformationEpsilon (1e-6);
			// Set the maximum distance between two correspondences (src<->tgt) to 10cm
			// Note: adjust this based on the size of your datasets
			reg.setMaxCorrespondenceDistance (0.1);

			reg.setInputCloud (src);
			reg.setInputTarget (tgt);

			Eigen::Matrix4f Ti = Eigen::Matrix4f::Identity (), prev, targetToSource;
			pcl::PointCloud<pcl::PointXYZ>::Ptr reg_result = src;
			reg.setMaximumIterations (2);
			for (int i = 0; i < 10; ++i)
			{
				// save cloud for visualization purpose
				src = reg_result;

				// Estimate
				reg.setInputCloud (src);
				reg.align (*reg_result);

				//accumulate transformation between each Iteration
				Ti = reg.getFinalTransformation () * Ti;

				//if the difference between this transformation and the previous one
				//is smaller than the threshold, refine the process by reducing
				//the maximal correspondence distance
				if (fabs ((reg.getLastIncrementalTransformation () - prev).sum ()) < reg.getTransformationEpsilon ())
				{
				  reg.setMaxCorrespondenceDistance (reg.getMaxCorrespondenceDistance () - 0.001);
				}
				
				prev = reg.getLastIncrementalTransformation ();
			}

			pcl::PointCloud<pcl::PointXYZ>::Ptr outputTransform (new pcl::PointCloud<pcl::PointXYZ>);
			targetToSource = Ti.inverse();
			pcl::transformPointCloud (*local_in, *outputTransform, targetToSource);

			pcl::PointCloud<pcl::PointXYZ> difference;
			pcl::SegmentDifferences<pcl::PointXYZ> dif;
			dif.setInputCloud (reg_result);
			dif.setTargetCloud (local_out);
			dif.setDistanceThreshold (0.085);
			//dif.setDistanceThreshold (0.1);
			dif.segment(difference);

			pcl::PointCloud<pcl::PointXYZ> resultLocal;

			resultLocal += *reg_result;
			resultLocal += *local_out;

			//pcl::PointCloud<pcl::PointXYZ>::Ptr resultFinal;
			//*resultFinal = resultLocal;*/

			*cloud_out = resultLocal;
			*cloud_test = resultLocal;
		}
	}
}

/*JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_icp(JNIEnv *, jobject) {
	if(cloud_out->points.size() == 0) {
		*cloud_out = *cloud_in;
	} else {
		if(newData) {
			pcl::PointCloud<pcl::PointXYZ>::Ptr src (new pcl::PointCloud<pcl::PointXYZ>);
			pcl::PointCloud<pcl::PointXYZ>::Ptr tgt (new pcl::PointCloud<pcl::PointXYZ>);
			pcl::VoxelGrid<PointT> grid;
			grid.setLeafSize (0.05, 0.05, 0.05);
			grid.setInputCloud (cloud_in);
			grid.filter (*src);

			grid.setInputCloud (cloud_out);
			grid.filter (*tgt);

			PointCloudWithNormals::Ptr points_with_normals_src (new PointCloudWithNormals);
			PointCloudWithNormals::Ptr points_with_normals_tgt (new PointCloudWithNormals);

			pcl::NormalEstimation<PointT, PointNormalT> norm_est;
			pcl::search::KdTree<pcl::PointXYZ>::Ptr tree (new pcl::search::KdTree<pcl::PointXYZ> ());
			norm_est.setSearchMethod (tree);
			norm_est.setKSearch (30);
			
			norm_est.setInputCloud (src);
			norm_est.compute (*points_with_normals_src);
			pcl::copyPointCloud (*src, *points_with_normals_src);

			norm_est.setInputCloud (tgt);
			norm_est.compute (*points_with_normals_tgt);
			pcl::copyPointCloud (*tgt, *points_with_normals_tgt);

			pcl::IterativeClosestPointNonLinear<PointNormalT, PointNormalT> reg;
			reg.setTransformationEpsilon (1e-6);
			// Set the maximum distance between two correspondences (src<->tgt) to 10cm
			// Note: adjust this based on the size of your datasets
			reg.setMaxCorrespondenceDistance (0.1);  
			// Set the point representation
			//reg.setPointRepresentation (boost::make_shared<const MyPointRepresentation> (point_representation));

			reg.setInputCloud (points_with_normals_src);
			reg.setInputTarget (points_with_normals_tgt);

			Eigen::Matrix4f Ti = Eigen::Matrix4f::Identity (), prev, targetToSource;
			PointCloudWithNormals::Ptr reg_result = points_with_normals_src;
			reg.setMaximumIterations (2);
			for (int i = 0; i < 30; ++i)
			{
			  //PCL_INFO ("Iteration Nr. %d.\n", i);

			  // save cloud for visualization purpose
			  points_with_normals_src = reg_result;

			  // Estimate
			  reg.setInputCloud (points_with_normals_src);
			  reg.align (*reg_result);

			  //accumulate transformation between each Iteration
			  Ti = reg.getFinalTransformation () * Ti;

			  //if the difference between this transformation and the previous one
			  //is smaller than the threshold, refine the process by reducing
			  //the maximal correspondence distance
			  if (fabs ((reg.getLastIncrementalTransformation () - prev).sum ()) < reg.getTransformationEpsilon ())
			  {
			    reg.setMaxCorrespondenceDistance (reg.getMaxCorrespondenceDistance () - 0.001);
			  }
			  
			  prev = reg.getLastIncrementalTransformation ();
			  //PCL_INFO ("Size result(%d).\n", reg_result->points.size());
			  // visualize current state
			  //showCloudsRight(points_with_normals_tgt, points_with_normals_src);
			}
			pcl::PointCloud<pcl::PointXYZ>::Ptr output (new pcl::PointCloud<pcl::PointXYZ>);
			targetToSource = Ti.inverse();
			pcl::transformPointCloud (*cloud_in, *output, targetToSource);

			//pcl::PointCloud<pcl::PointXYZ> cloud_outLocal;
			//pcl::PointCloud<pcl::PointXYZ> outputLocal;
			//cloud_outLocal = *cloud_out;
			//outputLocal = *output; 

			//pcl::PointCloud<pcl::PointXYZ>::Ptr output (new pcl::PointCloud<pcl::PointXYZ>);
			//pcl::PointCloud<pcl::PointXYZ>::Ptr temp (new pcl::PointCloud<pcl::PointXYZ>);
			//
			// Transform target back in source frame

			//PCL_INFO ("Size output_in Nr. %d.\n", output->points.size());
			//PCL_INFO ("Size output_out Nr. %d.\n", cloud_out->points.size());
			pcl::PointCloud<pcl::PointXYZ> difference;
			pcl::SegmentDifferences<pcl::PointXYZ> dif;
			dif.setInputCloud (output);
			dif.setTargetCloud (cloud_out);
			dif.setDistanceThreshold (0.085);
			dif.segment(difference);
			
			pcl::PointCloud<pcl::PointXYZ> resultLocal;

			resultLocal += difference;
			resultLocal += *cloud_out;

			pcl::PointCloud<pcl::PointXYZ>::Ptr resultFinal;
			*resultFinal = resultLocal;

			//temp = *cloud_out;
			//*temp += *output;
			//pcl::VoxelGrid<pcl::PointXYZ> sor;
			//sor.setInputCloud (temp);
			//sor.setLeafSize (0.02f, 0.02f, 0.02f);
			//sor.filter (*cloud_out);
			
			//std::stringstream ss;
			//ss << counter;
			//std::string s(ss.str());

			//pcl::io::savePCDFileASCII ("dataOut"+s+".pcd", *output);

			counter++; 

			*cloud_out = *cloud_in;

			//PCL_INFO ("Size output_out Nr. %d.\n", cloud_out->points.size());
			//pcl::copyPointCloud (difference,*cloud_out);
			
			//*cloud_out = *result;

			//PCL_INFO ("Size output_out2 Nr. %d.\n", cloud_out->points.size());
			//pcl::PointCloud<pcl::PointXYZ>::Ptr diffIcp(*difference);

			//*cloud_out += *diffIcp;
		}
	}
	return;
} */


JNIEXPORT void JNICALL Java_udpReader_PointCloudStitcher_KinectData(JNIEnv *env, jobject, jintArray inJNIArray) {
	pcl::PointCloud<pcl::PointXYZ>::Ptr cloud_in_new (new pcl::PointCloud<pcl::PointXYZ>);

	jint *depth = (env)->GetIntArrayElements(inJNIArray, NULL);
	jsize length = (env)->GetArrayLength(inJNIArray);

	cloud_in_new->width    = (w/skip);
	cloud_in_new->height   = (h/skip);
	cloud_in_new->is_dense = false;
	cloud_in_new->points.resize (cloud_in_new->width * cloud_in_new->height);


	int index_in = 0;
	for(int x=0; x<w; x+=skip) {
		for(int y=0; y<h; y+=skip) {
			int offset = x+y*w;

			double fx_d = 1.0 / 5.9421434211923247e+02;
			double fy_d = 1.0 / 5.9104053696870778e+02;
			double cx_d = 3.3930780975300314e+02;
			double cy_d = 2.4273913761751615e+02;
			double z =  rawDepthToMeters(depth[offset]);

			cloud_in_new->points[index_in].x = (float)((x - cx_d) * z * fx_d);
			cloud_in_new->points[index_in].y = (float)((y - cy_d) * z * fy_d);
			cloud_in_new->points[index_in].z = (float)(z);


			index_in = index_in+1;
        }
	}
	*cloud_in = *cloud_in_new;
	newData = true;
	return;
} 