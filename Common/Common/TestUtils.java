package Common;

import Constant.Constant;
import DataObjects.User;
import GuerrillaMail.GuerrillaMailPage;
import Railway.HomePage;
import Railway.RegisterPage;

public class TestUtils {
	public static User createActivatedAccount() {
		// 1. Generate email by GuerrillaMail
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();

		String username = Utilities.generateRandomUsername(); // tạo user random
		String email = mailPage.createEmailAndGetIt(username);

		User user = new User(email, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");

		// 2. Register account
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.clickCreateAnAccount();
		registerPage = registerPage.register(user);

		// 3. Activate account via email
		mailPage.open();
		mailPage.setMailboxUsername(username);
		mailPage.openConfirmEmail();
		mailPage.clickActivateLink();
		ProjectUtils.switchToLastWindow();

		return user;
	}

}
