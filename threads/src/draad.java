
public class draad extends Thread {
	private buff buf;
	
	public draad(buff buf) {
		this.buf = buf;		
	}
	
	public void run() {
		for(int i = 0; i < 10000; i++) {
			buf.setString(new Integer(i).toString());	
		}
	}
}
