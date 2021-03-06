package udpReader;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class readerThread extends Thread {
	private volatile boolean running = true;
	private boolean runICP = false;
	private buffer frameBuffer;
	private PointCloudStitcher stitcher;
	
	public readerThread(buffer buf,PointCloudStitcher stitcher) {
		this.frameBuffer = buf;
		this.stitcher = stitcher;
	}
	
	public static int[] convert(byte buf[]) {
		   int intArr[] = new int[buf.length / 2];
		   for(int i = 0; i < intArr.length; i= i+2) {
		      intArr[i] = ByteBuffer.wrap(new byte[] {buf[i], buf[i+1]}).order(ByteOrder.LITTLE_ENDIAN).getShort();
		   }
		   return intArr;
	}
	
	public static int[] convertTest(byte buf[],int length) {
		   int intArr[] = new int[(length-2) / 2];
		   int y = 0;
		   for(int i = 2; i < length; i= i+2) {
		      intArr[y] = ByteBuffer.wrap(new byte[] {buf[i], buf[i+1]}).order(ByteOrder.LITTLE_ENDIAN).getShort();
		      y++;
		   }
		   return intArr;
	}
	
	 public void run() {
		System.out.println("start");
		 try {
            int port = 8888;
            DatagramSocket dsocket = new DatagramSocket(port);
            byte[] buffer = new byte[914400];
            //byte[] buffer2 = new byte[0];
            int[] frames = new int[307200];
            boolean frame = false;
            stitcher = new PointCloudStitcher();
 
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
            	while (running) {
            		dsocket.receive(packet);
                
            		if(packet.getLength() < 6) {
            			String msg = new String(buffer, 0, packet.getLength());

            			if(msg.equals("start") == true) {
            				System.out.println("start");
            				frame = true;
            				//buffer2 = new byte[0];
            				frames = new int[307200];
            			}
            			if(msg.equals("end") == true) {
            				System.out.println("end");
            				frame = false;
            				
            				if(frames.length == 307200) {
            					stitcher.KinectData(frames);
            					/*stitcher.KinectData(frames);
            					//stitcher.icp();
            					//stitcher.getInputPoints();
            					//Point[] results  = (Point[])stitcher.getPoints();
            					Point[] results = (Point[])stitcher.getInputPoints();
            					System.out.println("c++"+results.length); */
            					
            					Point[] results = new Point[0];
                				if(runICP) {
                					//System.out.println("stitch");
                					stitcher.icp();
                					results = (Point[])stitcher.getPoints();
                				} else {
                					results = (Point[])stitcher.getInputPoints();
                				}
            					
            					frameBuffer.setFrame(results);
            				}
            			}
                	
            		} else {
            			if(frame == true) {
            				byte[] data = packet.getData();
            				int framenumber = ByteBuffer.wrap(new byte[] {data[0], data[1]}).order(ByteOrder.LITTLE_ENDIAN).getShort();
            				int[] intdata = convertTest(data,packet.getLength());
            				int nr = 0;
                		
            				nr = (framenumber*32000);
                	
            				System.arraycopy(intdata,0,frames,nr,(intdata.length));
                		
            			}
            		}

            		packet.setLength(buffer.length);
            	}
            }
            
            //dsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	 
	public void pauseThread() throws InterruptedException
	{
		running = false;
	}

	public void resumeThread()
	{
		running = true;
	}
	public boolean checkStatus() {
		return running;
	}
		
	public void setICPRun(Boolean run) {
		this.runICP = run;
	}
}
