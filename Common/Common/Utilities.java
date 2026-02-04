package Common;

public class Utilities {
	public static String generateRandomEmail() {
		long timestamp = System.currentTimeMillis();
		return "testuser" + timestamp + "@gmail.com";
	}
}
