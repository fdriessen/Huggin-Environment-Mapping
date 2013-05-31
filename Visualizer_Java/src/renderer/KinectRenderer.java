package renderer;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import org.openkinect.processing.*;

import processing.core.PVector;

public class KinectRenderer extends Renderer {

	public int size = 4;
	public Kinect kinect;
	float[] depthLookUp = new float[2048];
	int w = 640;
    int h = 480;
	
	public void visualize(GL gl) {
		
		int[] depth = kinect.getRawDepth();
	
				
		gl.glPointSize(size);
		
		gl.glBegin(GL.GL_POINTS);        // Draw The Cube Using quads
		
		int skip = 1;

		gl.glEnable(gl.GL_PROGRAM_POINT_SIZE_EXT);
		
		for(int x=0; x<w; x+=skip) {
            for(int y=0; y<h; y+=skip) {
              int offset = x+y*w;

              // Convert kinect data to world xyz coordinate
              int rawDepth = depth[offset];
              PVector v = depthToWorld(x,y,rawDepth);

              float factor = 200;

              gl.glVertex3f(v.x,v.y,v.z);
              float[] color = depth2rgb((short) rawDepth);
             
              //System.out.println(color[2]);
              
              //gl.glColor3i(255, 128, 0);
              gl.glColor3f(color[0],color[1],color[2]);
           }
          }
		//gl.glColor3f(1.0f,0.5f,0.0f);
		
	gl. glEnd();            // End Drawing The Cube
		
	}
	
	float rawDepthToMeters(int depthValue) {
        if (depthValue < 2047) {
          return (float)(1.0 / ((double)(depthValue) * -0.0030711016 + 3.3309495161));
        }
        return 0.0f;
      }

      PVector depthToWorld(int x, int y, int depthValue) {

        final double fx_d = 1.0 / 5.9421434211923247e+02;
        final double fy_d = 1.0 / 5.9104053696870778e+02;
        final double cx_d = 3.3930780975300314e+02;
        final double cy_d = 2.4273913761751615e+02;

        PVector result = new PVector();
        double depth =  rawDepthToMeters(depthValue);
        result.x = (float)((x - cx_d) * depth * fx_d);
        result.y = (float)((y - cy_d) * depth * fy_d);
        result.z = (float)(depth);
        return result;
      }
      
      static float[] depth2rgb(short depth)
  	{
  		int r,g,b;

  		float v = depth / 2047f;
  		v = (float) Math.pow(v, 3)* 6;
  		v = v*6*256;

  		int pval = Math.round(v);
  		int lb = pval & 0xff;
  		switch (pval>>8) {
  		case 0:
  			b = 255;
  			g = 255-lb;
  			r = 255-lb;
  			break;
  		case 1:
  			b = 255;
  			g = lb;
  			r = 0;
  			break;
  		case 2:
  			b = 255-lb;
  			g = 255;
  			r = 0;
  			break;
  		case 3:
  			b = 0;
  			g = 255;
  			r = lb;
  			break;
  		case 4:
  			b = 0;
  			g = 255-lb;
  			r = 255;
  			break;
  		case 5:
  			b = 0;
  			g = 0;
  			r = 255-lb;
  			break;
  		default:
  			r = 0;
  			g = 0;
  			b = 0;
  			break;
  		}

  		/*int pixel = (0xFF) << 24
  		| (b & 0xFF) << 16
  		| (g & 0xFF) << 8
  		| (r & 0xFF) << 0; */
  		float pixel[] = new float[3];
  		pixel[0] = (float)((1.0/255.0)*r);
  		pixel[1] = (float)((1.0/255.0)*g);
  		pixel[2] = (float)((1.0/255.0)*b);
  		
  		//System.out.println(pixel[0]);

  		return pixel;
  	}
}
