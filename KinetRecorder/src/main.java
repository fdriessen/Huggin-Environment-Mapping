import java.io.*;



import java.util.*;

public class main {

	List<int[]> testmap = new ArrayList<int[]>();
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		main test = new main();
		
		//test.write();
		test.read();
	}
	
	public void write() throws IOException {
		
		
		/*FileOutputStream file = new FileOutputStream("text.txt");
        ObjectOutputStream stream = new ObjectOutputStream(file);
		
		int[] object = {1,2,3,4,5,6,7}; 
		
		stream.writeObject(object);
		
		stream.close(); */
		System.out.println("write");
	}
	
	public int[] read(int index) throws IOException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream("text.txt");
		ObjectInputStream restore = new ObjectInputStream(fis);
			
		testmap = (ArrayList<int[]>)restore.readObject();
		restore.close();
		return testmap.get(index);
		
		/*for(int i=0; i < testmap.size(); i++) {
			int test[] = testmap.get(i);
			System.out.println("array:"+ test);
		}*/
		
	}



}
