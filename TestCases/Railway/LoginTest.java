package Railway;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import DataObjects.User;

public class LoginTest extends GeneralTest {

	@Test
	public void TC01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");

		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// 2. Click on "Login" tab
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();

		// 3. Enter valid Email and Password
		// 4. Click on "Login" button
		System.out.println("3. Enter valid Email and Password and 4. Click on \"Login\" button ");

		User validUser = new User(Constant.USERNAME, Constant.PASSWORD);
		String actualMsg = loginPage.login(validUser).getWelcomeMessage();

		// Verify welcome message
		String expectedMsg = "Welcome " + Constant.USERNAME;
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}

	@DataProvider(name = "invalidLoginData")
	public Object[][] invalidLoginData() {
		return new Object[][] {
				{ "TC02 - User cannot login with blank \\\"Username\\\" textbox", new User("", Constant.PASSWORD),
						"There was a problem with your login and/or errors exist in your form." },
				{ "TC03 - User cannot log into Railway with invalid password",
						new User(Constant.USERNAME, "invalidPassword"),
						"There was a problem with your login and/or errors exist in your form." }, };
	}

	@Test(dataProvider = "invalidLoginData")
	public void TC02_TC03(String caseName, User user, String expectedErrorMsg) {
		System.out.println(caseName);

		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// 2. Click on "Login" tab
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();

		// 3. User enter invalid data
		// 4. Click on "Login" button
		System.out.println("3. User enter invalid data and 4. Click \"Login\" button");
		loginPage.login(user);

		// Verify error message
		String actualErrorMsg = loginPage.getLoginErrorMessage();

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC04() {
		System.out.println("TC04 - System shows message when user enters wrong password many times");

		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// 2. Click on "Login" tab
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage(); // sửa cái nì

		// 3. Enter valid information into "Username" textbox except "Password" textbox.
		// 4. Click on "Login" button
		// 5. Repeat step 3 and 4 three more times.

		String expectedErrorMsg = "Invalid username or password. Please try again.";
		String expected4thErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

		System.out.println("5. Repeat step 3 and 4 three more times");
		for (int i = 1; i <= 4; i++) {

			// clear data giữa các lần nhập
			loginPage.clearUsername();
			loginPage.clearPassword();

			User invalidUser = new User(Constant.USERNAME, "invalid");
			loginPage.login(invalidUser);

			String actualErrorMsg = loginPage.getLoginErrorMessage();

			if (i <= 3) {
				System.out.println(
						"3. Enter valid information into \"Username\" textbox except \"Password\" textbox and 4. Click on \"Login\" button ");
				Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
			} else {
				Assert.assertEquals(actualErrorMsg, expected4thErrorMsg, "Error message is not displayed as expected");
			}
		}
	}

	@Test
	public void TC05() {
		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// Pre-condition: a not-active account is existing
		// Click on "Register" tab
		RegisterPage registerPage = homePage.gotoRegisterPage();

		// Create new account
		User notActivateUser = new User(Utilities.generateRandomEmail(), Constant.PASSWORD, Constant.CONFIRMPASSWORD,
				"12345678");
		registerPage.register(notActivateUser);

		// 2. Click on "Login" tab
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();

		// 3. Enter username and password of account hasn't been activated
		// 4. Click on "Login" button
		System.out
				.println("3. Enter username and password of account hasn't been activated and 4. Click \"Login\" button");
		loginPage.login(notActivateUser);

		// Verify error message
		String actualErrorMsg = loginPage.getLoginErrorMessage();
		String expectedErrorMsg = "Invalid username or password. Please try again.";

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");

	}

}