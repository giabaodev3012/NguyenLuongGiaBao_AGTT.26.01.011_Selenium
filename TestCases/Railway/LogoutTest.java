package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;

public class LogoutTest extends GeneralTest {

	@Test
	public void TC06() {
		System.out.println("TC06 - User is redirected to Home page after logging out");

		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// 2. Login with valid Email and Password
		System.out.println("2. Login with valid Email and Password");
		// Click login tab
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		// Enter valid information
		User validUser = new User(Constant.USERNAME, Constant.PASSWORD);
		loginPage.login(validUser,HomePage.class);

		// 3. Click on "FAQ" tab
		System.out.println("3. Click on \"FAQ\" tab");
		homePage.gotoPage(MenuTab.FAQ, FAQPage.class);

		// 4. Click on "Log out" tab
		System.out.println("4. Click on \"Log out\" tab");
		HomePage afterLogoutHomePage = homePage.gotoPage(MenuTab.LOGOUT, HomePage.class);

		// Verify homepage displays
		boolean isHomePageDisplayed = afterLogoutHomePage.isHomePageDisplayed();
		Assert.assertTrue(isHomePageDisplayed, "\"Home page is NOT displayed after logging out.\"");

		// Verify "Log out" tab is disappeared
		boolean isLogoutTabDisappeared = afterLogoutHomePage.isLogoutTabUndisplayed();
		Assert.assertTrue(isLogoutTabDisappeared, "\"Log out\" tab is still displayed after logging out");
	}
}
