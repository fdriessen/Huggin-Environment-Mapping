import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.openkinect.*;
import org.openkinect.processing.*;

import processing.core.PApplet;

import javax.media.opengl.GLCanvas;

import OpenGL.OpenGL;
import renderer.KinectRenderer;
import renderer.TestRenderer;

public class MainClass {
	Kinect kinect;
	public MainClass() {
		Frame frame = new Frame("OpenGL");
		GLCanvas canvas = new GLCanvas();
		
		OpenGL openGL = new OpenGL(canvas);
		
		TestRenderer renderer = new TestRenderer();
		
		openGL.addRenderer(renderer);
		
		canvas.addGLEventListener(openGL);
		
		renderer.size = 1;
		
		PApplet kinectApplet = new PApplet();
		
		/*kinect = new Kinect(kinectApplet);
		kinect.start();
		kinect.enableDepth(true);
		kinect.processDepthImage(false);
		kinect.tilt(-1f);
		
		renderer.kinect = kinect;*/
		
		frame.add(kinectApplet);
		
		//hallo.setVisible(false);
		
		frame.add(canvas);
		
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
        
    }
	
	public static void main(String[] args) {
		new MainClass();
	}
}