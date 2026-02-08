package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.TestUtils;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;
import GuerrillaMail.GuerrillaMailPage;

public class LoginTest extends TestBase {

	@Test
	public void TC01() {
		System.out.println("Prepare data");
		User validUser = TestUtils.createActivatedAccount();
		String expectedMsg = "Welcome " + validUser.getUsername();

		System.out.println("TC01 - User can log into Railway with valid username and password");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println("3. Enter valid Email and Password");
		System.out.println("4. Click on \"Login\" button");
		homePage = loginPage.login(validUser, HomePage.class);

		System.out.println("VP: User is logged into Railway. Welcome user message is displayed. ");
		String actualMsg = homePage.getWelcomeMessage();
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}

	@Test
	public void TC02() {
		System.out.println("Prepare data");
		User invalidUser = new User("", Constant.PASSWORD);
		String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";

		System.out.println("TC02 - User cannot login with blank \\\"Username\\\" textbox");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println(
				"3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox");
		System.out.println("4. Click \"Login\" button");
		loginPage = loginPage.login(invalidUser, LoginPage.class);

		System.out.println(
				"VP: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		String actualErrorMsg = loginPage.getLoginErrorMessage();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC03() {
		System.out.println("Prepare data");
		User activateUser = TestUtils.createActivatedAccount();
		User invalidUser = new User(activateUser.getUsername(), "invalidPassword");
		String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";

		System.out.println("TC03 - User cannot log into Railway with invalid password");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println("3. Enter valid Email and invalid Password");
		System.out.println("4. Click \"Login\" button");
		loginPage = loginPage.login(invalidUser, LoginPage.class);

		System.out.println(
				"VP: Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		String actualErrorMsg = loginPage.getLoginErrorMessage();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC04() {
		System.out.println("Prepare data");
		User activateUser = TestUtils.createActivatedAccount();
		User invalidUser = new User(activateUser.getUsername(), "invalid");
		
		String expectedErrorMsg = "Invalid username or password. Please try again.";
		String expected4thErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

		System.out.println("TC04 - System shows message when user enters wrong password many times");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println("5. Repeat step 3 and 4 three more times");
		for (int i = 1; i <= 4; i++) {
			loginPage = loginPage.login(invalidUser, LoginPage.class);
			String actualErrorMsg = loginPage.getLoginErrorMessage();

			if (i <= 3) {
				System.out.println(
						"3. Enter valid information into \"Username\" textbox except \"Password\" textbox and 4. Click on \"Login\" button ");
				System.out.println("VP: \"Invalid username or password. Please try again\" is shown");
				Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
			} else {
				System.out.println(
						"VP: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
				Assert.assertEquals(actualErrorMsg, expected4thErrorMsg, "Error message is not displayed as expected");
			}
		}
	}

	@Test
	public void TC05() {
		System.out.println("Prepare data");
		String expectedErrorMsg = "Invalid username or password. Please try again.";
		// Generate email by GuerrillaMail
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();

		String username = Utilities.generateRandomUsername(); // tạo user random
		String email = mailPage.createEmailAndGetIt(username);

		User notActivateUser = new User(email, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");

		System.out.println("TC05 - User can't login with an account hasn't been activated");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Pre-condition: a not-active account is existing");
		// Click on "Register" tab
		RegisterPage registerPage = homePage.gotoPage(MenuTab.REGISTER, RegisterPage.class);
		// Create new account
		registerPage = registerPage.register(notActivateUser);

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = registerPage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println("3. Enter username and password of account hasn't been activated");
		System.out.println("4. Click \"Login\" button");
		loginPage = loginPage.login(notActivateUser, LoginPage.class);

		System.out.println(
				"VP: User can't login and message \"Invalid username or password. Please try again.\" appears.");
		String actualErrorMsg = loginPage.getLoginErrorMessage();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

}