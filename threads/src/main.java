
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		main test = new main();
		test.start();
	}
	
	public void start() {
		System.out.println("test");
		buff buf = new buff();
		draad thread = new draad(buf);
		
		thread.start();
		
		thread.suspend();
		while(true) {
			
			System.out.println(buf.getString());
		}
		
		
		
	}

}
