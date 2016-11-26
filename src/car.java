import java.util.Vector;
import java.util.ListIterator;
import helper.random;

public class car extends Thread{
	public static final int WEIGHT_MAX = 1000;
	public static final int TOUR_TIME_MIN = 3;
	public static final int TOUR_TIME_MAX = 5;
	private int current_weight = 0;
	private Vector<passenger> passengers = new Vector<passenger>();
	
	public boolean load(passenger passenger) {
		//current weight does not exceed weight limit then load the passenger
		if( (current_weight + passenger.getWeight()) < WEIGHT_MAX ) {
			current_weight += passenger.getWeight();
			passenger.notify();
			passengers.addElement(passenger);
			return true;
		}
		return false;
	}
	
	public void unload() {
		ListIterator<passenger> collection = passengers.listIterator();
		while(collection.hasNext()) {
			collection.next().getOffCar();
		}
	}
	
	public void tour() {
		try {
			int sleepTime = random.randomGenerate(0, random.randomGenerate(TOUR_TIME_MIN, TOUR_TIME_MAX));
			car.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unload();
	}

	public int getCurrentWeight() {
		return this.current_weight;
	}
}
