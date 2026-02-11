package Common;

import org.openqa.selenium.WindowType;

import Constant.Constant;
import DataObjects.User;
import GuerrillaMail.GuerrillaMailPage;
import Railway.HomePage;
import Railway.RegisterPage;

public class TestUtils {
	public static User createActivatedAccount() {

		// Lưu lại định danh (handle) của tab chính
		String currentWindow = Constant.WEBDRIVER.getWindowHandle();

		// Mở GuerrillaMail ở tab mới
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
		String mailWindow = Constant.WEBDRIVER.getWindowHandle();

		// Generate email by GuerrillaMail
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();

		String username = Utilities.generateRandomUsername(); // tạo user random
		String email = mailPage.createEmailAndGetIt(username);

		User user = new User(email, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");

		// 2. Register account
		Constant.WEBDRIVER.switchTo().window(currentWindow); // quay lại tab cũ
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.clickCreateAnAccount();
		registerPage = registerPage.register(user);

		// 3. Activate account via email
		Constant.WEBDRIVER.switchTo().window(mailWindow);
		mailPage.openConfirmEmail();
		ProjectUtils.switchToLastWindow();
		String activationWindow = Constant.WEBDRIVER.getWindowHandle();

		// Đóng các tab không cần thiết
		// Đóng tab Mail
		Constant.WEBDRIVER.switchTo().window(mailWindow);
		Constant.WEBDRIVER.close();

		// Đóng tab Đăng ký
		Constant.WEBDRIVER.switchTo().window(currentWindow);
		Constant.WEBDRIVER.close();
		
		Constant.WEBDRIVER.switchTo().window(activationWindow);

		return user;
	}
	
	public static String getQuickEmail() {
	    String currentWindow = Constant.WEBDRIVER.getWindowHandle();
	    
	    // Mở tab mới để lấy mail
	    Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    
	    String username = Utilities.generateRandomUsername(); // tạo user random
	    String email = mailPage.createEmailAndGetIt(username);
	    
	    // Đóng tab mail và quay về tab chính ngay lập tức
	    Constant.WEBDRIVER.close();
	    Constant.WEBDRIVER.switchTo().window(currentWindow);
	    
	    return email;
	}

}
