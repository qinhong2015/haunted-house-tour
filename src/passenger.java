import helper.random;
import helper.console;

public class passenger extends Thread{
	
    public static final int WEIGHT_MIN = 70;
    public static final int WEIGHT_MAX = 200;
    public static final int WANDER_TIME_MIN = 7;
    public static final int WANDER_TIME_MAX = 9;
    public static final int TOUR_RIDE_MAX = 4;
    private int tripCount = 0;
    private Object lock = new Object();
    private int weight;
    private console console;
    
    public passenger(int id, console console){
    	super("Passenger" + id);
    	this.weight = generateWeight();
    	this.console = console;
    }
    
    public void waitForCar(){
        synchronized (lock) {
           while (true) { // wait to be notified, not interrupted
        	   try { lock.wait(); break; }
        	   // notify() after interrupt() race condition ignored
        	   catch (InterruptedException e) { 
				  continue; 
        	   }
           }
        }
    }
    
    public void getOffCar(){
		try {
			int sleepTime = random.randomGenerate(0, random.randomGenerate(WANDER_TIME_MIN, WANDER_TIME_MAX));
			passenger.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( tripCount != TOUR_RIDE_MAX ) {
			tripCount++;
			waitForCar();
		} else {
			goHome();
		}
    }
    
    private void goHome(){
    	console.message(this.getName(), "went home");    
    }
    
    private int generateWeight(){
    	return random.randomGenerate(WEIGHT_MIN, WEIGHT_MAX);
    }
    
    public int getWeight(){
    	return this.weight;
    }
    
}
