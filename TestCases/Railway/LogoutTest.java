package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;

public class LogoutTest extends TestBase {

	@Test
	public void TC06() {
		System.out.println("Prepare data");
		User validUser = new User(Constant.USERNAME, Constant.PASSWORD);
		
		System.out.println("TC06 - User is redirected to Home page after logging out");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with valid Email and Password");
		// Click login tab
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);
		// Enter valid information
		homePage = loginPage.login(validUser, HomePage.class);

		System.out.println("3. Click on \"FAQ\" tab");
		FAQPage faqPage = homePage.gotoPage(MenuTab.FAQ, FAQPage.class);

		System.out.println("4. Click on \"Log out\" tab");
		homePage = faqPage.gotoPage(MenuTab.LOGOUT, HomePage.class);

		System.out.println("VP: Home page displays.");
		Assert.assertTrue(homePage.isHomePageDisplayed(), "\"Home page is NOT displayed after logging out.\"");

		System.out.println("VP: \"Log out\" tab is disappeared.");
		Assert.assertTrue(homePage.isLogoutTabDisappeared(), "\"Log out\" tab is still displayed after logging out");
	}
}
