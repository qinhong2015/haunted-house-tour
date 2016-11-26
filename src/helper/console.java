package helper;

public class console {
	
	private long startTime = System.currentTimeMillis();
	
	public long age() { 
		return System.currentTimeMillis() - startTime;
	}
	
	public void message(String name, String message) {
		System.out.println("["+age()+"] "+ name + message);
	}
}
