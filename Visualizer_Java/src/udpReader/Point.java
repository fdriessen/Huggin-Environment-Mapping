package udpReader;

import java.io.Serializable;

public class Point implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float x;
	public float y;
	public float z;
	public Point() {
		this.x = 1.0f;
		this.y = 2.0f;
		this.z = 3.0f;	
	}
	public Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;	
	}
	
	public void print() {
		System.out.println("x:"+this.x+" y:"+this.y+" z:"+this.z);
	}
}
