package Common;
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
}
