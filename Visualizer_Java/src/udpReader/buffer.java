package udpReader;

public class buffer {
	private Point[] frame = new Point[0];
	
	public synchronized void setFrame(Point[] frame) {
		
		this.frame = frame;
	}
	
	public synchronized Point[] getFrame() {
		return this.frame;
	}
}
