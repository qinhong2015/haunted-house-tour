package helper;
import java.util.concurrent.ThreadLocalRandom;

public class random {
	public static int randomGenerate(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
