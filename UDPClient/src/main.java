import java.io.*; 
import java.net.*; 
import java.nio.*;
import java.util.ArrayList;
import java.util.List;

class main 
{    
	
	public static int[] convert(byte buf[]) {
		   int intArr[] = new int[buf.length / 2];
		   for(int i = 0; i < intArr.length; i= i+2) {
		      intArr[i] = ByteBuffer.wrap(new byte[] {buf[i], buf[i+1]}).order(ByteOrder.LITTLE_ENDIAN).getShort();
		   }
		   return intArr;
		}
	
	public static void main(String args[]) throws Exception    
	{       		
		List<int[]> writetestmap = new ArrayList<int[]>();
		int counterWrite = 0;
		try {
            int port = 8888;
            DatagramSocket dsocket = new DatagramSocket(port);
            byte[] buffer = new byte[814400];
            byte[] buffer2 = new byte[0];
            boolean frame = false;
 
            DatagramPacket packet = new DatagramPacket(buffer,
                buffer.length);
            
            int counter = 0;
            while (true) {
                //System.out.println("Receiving...");
                dsocket.receive(packet);
                
                if(packet.getLength() < 6) {
                	String msg = new String(buffer, 0, packet.getLength());
                	//System.out.println(packet.getAddress().getHostName() + "test" + packet.getLength() +  ": " + msg);
                	
                	if(msg.equals("start") == true) {
                		counter = 0;
                		frame = true;
                		System.out.println("start");
                		buffer2 = new byte[0];
                	}
                	if(msg.equals("end") == true) {
                		frame = false;
                		/*IntBuffer intBuf =
                     		   ByteBuffer.wrap(buffer2)
                     		     .order(ByteOrder.LITTLE_ENDIAN)
                     		     .asIntBuffer();
                     		 int[] array = new int[intBuf.remaining()];
                     		 intBuf.get(array);*/
                		
                		int[] test = convert(buffer2);
                		
                		
                     		//writetestmap.add(convert(buffer2));
                		
                		System.out.println("stop - "+counter+ " - "+test.length);
                		counterWrite++;
                		if(counterWrite > 20) {
                        	//break;
                        }
                	}
                	
                } else {
                	if(frame == true) {
                		System.out.println(packet.getLength());
                		counter++;
                		byte[] temp = new byte[buffer2.length + (packet.getLength()-2)];
                		System.arraycopy(buffer2,0,temp,0,buffer2.length);
                		System.arraycopy(packet.getData(),2,temp,buffer2.length,(packet.getLength()-2));
                		
                		//System.arraycopy(packet.getData(),0,temp,1,packet.getLength());
                		//System.arraycopy(buffer2,0,temp,packet.getLength(),buffer2.length);
                		buffer2 = temp;
                	}
                	//byte[] combined = new byte[one.length + two.length];
                	//System.out.println(packet.getAddress().getHostName() + "test" + packet.getLength() +  ": ");
                	
                }
                
               /* IntBuffer intBuf =
                		   ByteBuffer.wrap(buffer)
                		     .order(ByteOrder.LITTLE_ENDIAN)
                		     .asIntBuffer();
                		 int[] array = new int[intBuf.remaining()];
                		 intBuf.get(array);*/
                		 
               // int[] array = convert(buffer);
                
                //String msg = new String(buffer, 0, packet.getLength());
                
                //System.out.println(packet.getAddress().getHostName() + "test" + packet.getLength() +  ": ");
               // writetestmap.add(array);
                packet.setLength(buffer.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		System.out.println("klaar");
		
		try {
			//if(counterWrite == 50) {
				
				FileOutputStream file = new FileOutputStream("output123.txt");
		        ObjectOutputStream stream = new ObjectOutputStream(file);
		        stream.writeObject(writetestmap);
				
				stream.close();
				System.out.println("klaar");
				writetestmap = new ArrayList<int[]>();
			//}
			
		} catch (IOException e) {
			
			
		} 
		
	} 
}