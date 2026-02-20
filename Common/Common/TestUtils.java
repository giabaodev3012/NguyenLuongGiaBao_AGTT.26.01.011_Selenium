package Common;

import Constant.Constant;
import DataObjects.User;
import GuerrillaMail.GuerrillaMailPage;
import Railway.HomePage;
import Railway.RegisterPage;

public class TestUtils {
	public static User createActivatedAccount() {

		// Save the identifier (handle) of the main tab
		String currentTab = Constant.WEBDRIVER.getWindowHandle();

		// Open GuerrillaMail in a new tab
		String mailTab = ProjectUtils.openNewTab();

		// Generate email by GuerrillaMail
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();

		String username = Utilities.generateRandomUsername();
		String email = mailPage.createEmailAndGetIt(username);

		User user = new User(email, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");

		// 2. Register account
		ProjectUtils.switchToWindow(currentTab);
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.clickCreateAnAccount();
		registerPage = registerPage.register(user);

		// 3. Activate account via email
		ProjectUtils.switchToWindow(mailTab);
		mailPage.openConfirmEmail();
		ProjectUtils.switchToLastWindow();
		String activationTab = Constant.WEBDRIVER.getWindowHandle();

		// Close unnecessary tabs
		// Close the Mail tab
		ProjectUtils.switchToWindow(mailTab);
	    ProjectUtils.closeAndSwitchTo(currentTab);
	    ProjectUtils.closeAndSwitchTo(activationTab);

		return user;
	}

	public static String getQuickEmail() {
		String currentTab = Constant.WEBDRIVER.getWindowHandle();

		// Open a new tab to retrieve the email
		ProjectUtils.openNewTab();
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();

		String username = Utilities.generateRandomUsername(); // tạo user random
		String email = mailPage.createEmailAndGetIt(username);

		// Close the mail tab and immediately switch back to the main tab
		ProjectUtils.closeAndSwitchTo(currentTab);

		return email;
	}

}
