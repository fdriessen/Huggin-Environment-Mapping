package renderer;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import org.openkinect.processing.*;

import processing.core.PVector;

public class TestRenderer extends Renderer {

	public int size = 4;

	public void visualize(GL gl) {
		

		
		
gl.glEnable(gl.GL_PROGRAM_POINT_SIZE_EXT);
		
		gl.glPointSize(size);
		
		gl.glBegin(GL.GL_POINTS);        // Draw The Cube Using quads
		
		
		 //for(int x = 0; x < 100; x = x+1) {

			//gl.glVertex3f( (float) (x*0.01), (float) (x*0.01),-(float) (x*0.01)); 

		//} 
		gl.glColor3f(0.0f,1.0f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( (float) (x*0.1), 1.0f,1.0f); 

		}
		
		gl.glColor3f(1.0f,0.5f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( (float) (10*0.1), (float) (x*0.1),1.0f); 

		}
		
		gl.glColor3f(1.0f,0.5f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( -0.1f, (float) (x*0.1),1.0f); 

		}
		
		gl.glColor3f(1.0f,0.5f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( -0.1f, (float) (x*0.1),-0.1f); 

		}
		
		gl.glColor3f(1.0f,0.5f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( 1.0f, (float) (x*0.1),-0.1f); 

		}
		
		gl.glColor3f(1.0f,0.0f,0.0f);
		for(int x = 0; x < 11; x = x+1) {

			gl.glVertex3f( 1.0f, 1.0f,(float) (x*0.1)); 

		}
		
		gl.glColor3f(0.0f,1.0f,0.0f);
		for(int x = 0; x < 11; x = x+1) {

			gl.glVertex3f( -0.1f, 1.0f,(float) (x*0.1)); 

		}
		
		gl.glColor3f(0.0f,1.0f,0.0f);
		for(int x = 0; x < 11; x = x+1) {

			gl.glVertex3f( -0.1f, -0.1f,(float) (x*0.1)); 

		} 
		
		gl.glColor3f(0.0f,1.0f,0.0f);
		for(int x = 0; x < 11; x = x+1) {

			gl.glVertex3f( 1.0f, -0.1f,(float) (x*0.1)); 

		} 
		
		gl.glColor3f(0.0f,1.0f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( (float) (x*0.1), -0.1f,1.0f); 

		}
		
		gl.glColor3f(1.0f,0.0f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( (float) (x*0.1), -0.1f,-0.1f); 

		}
		
		gl.glColor3f(1.0f,0.0f,0.0f);
		for(int x = 0; x < 10; x = x+1) {

			gl.glVertex3f( (float) (x*0.1), 1.0f,-0.1f); 

		}
		

		
		/*gl.glColor3f(0.0f,1.0f,0.0f);    // Color Blue
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( (float) (x*0.01), -(float) (x*0.01),(float) (x*0.01)); 

		}
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( (float) (x*0.01), 0.0f,0.0f); 

		}
		
		gl.glColor3f(1.0f,0.5f,0.0f);    // Color Orange
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( -(float) (x*0.01), (float) (x*0.01),(float) (x*0.01)); 

		}
		
		gl.glColor3f(1.0f,0.0f,0.0f);    // Color Red 
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( -(float) (x*0.01), -(float) (x*0.01),(float) (x*0.01)); 

		}
		
		gl.glColor3f(1.0f,1.0f,0.0f);    // Color Yellow
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( -(float) (x*0.01), (float) (x*0.01),-(float) (x*0.01)); 

		}
		
		gl.glColor3f(0.0f,1.0f,0.0f);    // Color Blue
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( (float) (x*0.01), -(float) (x*0.01),-(float) (x*0.01)); 

		}
		
		gl.glColor3f(1.0f,0.0f,1.0f);    // Color Violet
		
		for(int x = 0; x < 100; x = x+1) {

			gl.glVertex3f( (float) (x*0.01), (float) (x*0.01),(float) (x*0.01)); 

		}*/
		
		
		/*
		gl.glVertex3f( 1.0f, 1.0f,-1.0f);    // Top Right Of The Quad (Top)
		gl.glVertex3f(-1.0f, 1.0f,-1.0f);    // Top Left Of The Quad (Top)
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);    // Bottom Left Of The Quad (Top)
		gl.glVertex3f( 1.0f, 1.0f, 1.0f);    // Bottom Right Of The Quad (Top)
		
		
		
		gl.glVertex3f( 1.0f,-1.0f, 1.0f);    // Top Right Of The Quad (Bottom)
		gl.glVertex3f(-1.0f,-1.0f, 1.0f);    // Top Left Of The Quad (Bottom)
		gl.glVertex3f(-1.0f,-1.0f,-1.0f);    // Bottom Left Of The Quad (Bottom)
		gl.glVertex3f( 1.0f,-1.0f,-1.0f);    // Bottom Right Of The Quad (Bottom)
		
		gl.glColor3f(1.0f,0.0f,0.0f);    // Color Red    
		
		gl.glVertex3f( 1.0f, 1.0f, 1.0f);    // Top Right Of The Quad (Front)
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);    // Top Left Of The Quad (Front)
		gl.glVertex3f(-1.0f,-1.0f, 1.0f);    // Bottom Left Of The Quad (Front)
		gl.glVertex3f( 1.0f,-1.0f, 1.0f);
		
		gl.glColor3f(1.0f,1.0f,0.0f);    // Color Yellow
		
		gl.glVertex3f( 1.0f,-1.0f,-1.0f);    // Top Right Of The Quad (Back)
		gl.glVertex3f(-1.0f,-1.0f,-1.0f);    // Top Left Of The Quad (Back)
		gl.glVertex3f(-1.0f, 1.0f,-1.0f);    // Bottom Left Of The Quad (Back)
		gl.glVertex3f( 1.0f, 1.0f,-1.0f);    // Bottom Right Of The Quad (Back)
		
		gl.glColor3f(0.0f,1.0f,0.0f);    // Color Blue
		
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);    // Top Right Of The Quad (Left)
		gl.glVertex3f(-1.0f, 1.0f,-1.0f);    // Top Left Of The Quad (Left)
		gl.glVertex3f(-1.0f,-1.0f,-1.0f);    // Bottom Left Of The Quad (Left)
		gl.glVertex3f(-1.0f,-1.0f, 1.0f);    // Bottom Right Of The Quad (Left)
		
		gl.glColor3f(1.0f,0.0f,1.0f);    // Color Violet
		
		gl.glVertex3f( 1.0f, 1.0f,-1.0f);    // Top Right Of The Quad (Right)
		gl.glVertex3f( 1.0f, 1.0f, 1.0f);    // Top Left Of The Quad (Right)
		gl.glVertex3f( 1.0f,-1.0f, 1.0f);    // Bottom Left Of The Quad (Right)
		gl.glVertex3f( 1.0f,-1.0f,-1.0f); */
	
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
}
