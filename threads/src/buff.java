
public class buff {
	private String inhoud;
	
	public synchronized String getString() {
		
		return this.inhoud;
	}
	
	public synchronized void setString(String name) {
		
		this.inhoud = name;
	}
}
