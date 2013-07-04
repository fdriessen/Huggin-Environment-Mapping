import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            int port = 8888;
            DatagramSocket dsocket = new DatagramSocket(port);
            byte[] buffer = new byte[614400];
            DatagramPacket packet = new DatagramPacket(buffer,
                    buffer.length);
                while (true) {
                	System.out.println("Receiving...");
                	dsocket.receive(packet);
                	
                	byte[] inhoud = packet.getData();
                	
                	if(packet.getLength() == 2) {
                		ByteBuffer bb = ByteBuffer.wrap(new byte[] {inhoud[0], inhoud[1]}).order(ByteOrder.LITTLE_ENDIAN);
                		
                		System.out.println(bb.getShort());
                	} 
                	
                	/* if(packet.getLength() < 6) {
                    	String msg = new String(buffer, 0, packet.getLength());
                    	System.out.println(msg);
                	} else {
                		IntBuffer intBuf =
                      		   ByteBuffer.wrap(buffer)
                      		     .order(ByteOrder.LITTLE_ENDIAN)
                      		     .asIntBuffer();
                      		 int[] array = new int[intBuf.remaining()];
                      		 intBuf.get(array);
                      		System.out.println(array);
                	} */
                	
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
                
	}

}
