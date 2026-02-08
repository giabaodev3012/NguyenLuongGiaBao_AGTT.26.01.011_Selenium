package Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {

	public static String generateRandomUsername() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmm");
		return "test" + dtf.format(LocalDateTime.now());
	}
}
