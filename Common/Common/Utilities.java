package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utilities {
	public static final String dateFormat = "M/d/yyyy";
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern(dateFormat);

	public static String generateRandomUsername() {
		// Get the last 3 digits of the timestamp
		long stamp = System.currentTimeMillis() % 1000;

		// Generate 3 additional random digits from 100 to 999
		int rand = new Random().nextInt(900) + 100;

		// Combine them into a 6-character string
		// Example result: test582193
		return "test" + String.format("%03d", stamp) + rand;
	}

	public static String getUsernameFromEmail(String email) {
		return email.split("@")[0];
	}

	public static String plusDaysFromCurrentDepartDate(String date, int plusDays) {
		LocalDate localDate = LocalDate.parse(date, fmt);
		return localDate.plusDays(plusDays).format(fmt);
	}

	public static String getToday() {
		return LocalDate.now().format(fmt);
	}

}
