import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class testGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<int[][]> writetestmap = new ArrayList<int[][]>();
		int[][] test  = new int[480][640];
		
		System.out.println(test.length);
		int i = 0;
		for(int x =0; x < test.length; x++) {
		
			for(int y = 0; y < test[x].length; y++) {
				test[x][y] = i;
				
				i++;
			}
		}
		
		writetestmap.add(test);
		writetestmap.add(test);
		writetestmap.add(test);
		writetestmap.add(test);
		writetestmap.add(test);
		writetestmap.add(test);
		writetestmap.add(test);
		
		System.out.println(test);
		
		try {
			//if(counterWrite == 50) {
				
				FileOutputStream file = new FileOutputStream("outputxy.txt");
		        ObjectOutputStream stream = new ObjectOutputStream(file);
		        stream.writeObject(writetestmap);
				
				stream.close();
				System.out.println("klaar");
				//writetestmap = new ArrayList<int[]>();
			//}
			
		} catch (IOException e) {
			
			
		}
	}

}
