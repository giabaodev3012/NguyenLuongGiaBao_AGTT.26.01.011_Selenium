package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utilities {

	public static String generateRandomUsername() {
		// Lấy 3 số cuối của timestamp
		long stamp = System.currentTimeMillis() % 1000;

		// Tạo thêm 3 số ngẫu nhiên từ 100 đến 999
		int rand = new Random().nextInt(900) + 100;

		// Kết hợp lại thành 6 ký tự
		// Ví dụ kết quả: test582193
		return "test" + String.format("%03d", stamp) + rand;
	}

	public static String getUsernameFromEmail(String email) {
		return email.split("@")[0];
	}

	public static String plusDaysFromCurrentDepartDate(String date, int plusDays) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate localDate = LocalDate.parse(date, fmt);
		return localDate.plusDays(plusDays).format(fmt);
	}

	public static String getToday() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M/d/yyyy");
		return LocalDate.now().format(fmt);
	}

}
