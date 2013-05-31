import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;

import org.openkinect.processing.Kinect;

//import javax.media.opengl.GLCanvas;

import processing.core.PApplet;

public class MainTestClass {
	Kinect kinect;
	
	public MainTestClass() {
		System.out.print("test");
		//Frame frame = new Frame("OpenGL");
//GLCanvas canvas = new GLCanvas();
		
		//OpenGL openGL = new OpenGL(canvas);
		//canvas.addGLEventListener(openGL);
		//try{
			PApplet test = new PApplet();
			
			//Frame frame = new Frame("OpenGL");
			
			//test.setVisible(false);
			kinect = new Kinect(test);
			kinect.start();
			//kinect.enableDepth(false);
			//kinect.enableRGB(false);
			//kinect.enableIR(false);
			kinect.tilt(15f);
		//} catch(Exception test) {
		//	System.out.print(test.getMessage());
			
		//}
			
			//frame.add(test);
			//frame.add(canvas);
			//frame.setSize(600, 600);
			//frame.setVisible(true);
			//frame.addWindowListener(new WindowAdapter() {
			//	public void windowClosing(WindowEvent e) {
			//		System.exit(0);
			////	}
			//});
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainTestClass();
	}

}
