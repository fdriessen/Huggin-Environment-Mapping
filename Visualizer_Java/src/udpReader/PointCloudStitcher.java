package udpReader;


public class PointCloudStitcher {
	public native void icp();
	public native void KinectData(int[] depth);
	public native void saveFrameFile(int nr);
	public native void saveOutputCloud();
	public native void loadOutputCloud();
	public native void resetOutputCloud();
	public native void setInputOutput();
	public native Object[] getPoints();
	public native Object[] getInputPoints();
    static {
        System.loadLibrary("PointCloudStitcher");
    }
}
