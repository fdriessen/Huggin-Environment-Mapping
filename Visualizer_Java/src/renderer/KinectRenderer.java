package renderer;

import javax.media.opengl.GL;

//import com.sun.opengl.util.GLUT;

//import org.openkinect.processing.*;

import processing.core.PVector;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import udpReader.buffer;
import udpReader.Point;
import udpReader.ListPoints;

public class KinectRenderer extends Renderer {

	public int size = 4;
	public buffer frameBuffer;

    public void visualize(GL gl) {
    	gl.glPointSize(size);
		
		gl.glBegin(GL.GL_POINTS);
		gl.glEnable(gl.GL_PROGRAM_POINT_SIZE_EXT);

		Point[] pointField = frameBuffer.getFrame();
		if(pointField.length != 0) {
			for(int i=0; i<pointField.length; i++) {	
				Point point = pointField[i];
				gl.glVertex3f(point.x,point.y,point.z);

			}
		}
		
		gl.glColor3f(1.0f,1.0f,1.0f);
		gl. glEnd();
    }
}
