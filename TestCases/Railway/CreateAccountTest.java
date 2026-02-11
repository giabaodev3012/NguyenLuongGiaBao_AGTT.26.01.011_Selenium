package Railway;

import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.ProjectUtils;
import Common.TestUtils;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;
import GuerrillaMail.GuerrillaMailPage;

public class CreateAccountTest extends TestBase {

	@Test
	public void TC07() {
		System.out.println("Prepare data");
		String expectedErrorMsg = "This email address is already in use.";

		System.out.println("TC07 - User can't create account with an already in-use email");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Pre-condition: Create Activate Account");
		User activeUser = TestUtils.createActivatedAccount();

		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoPage(MenuTab.REGISTER, RegisterPage.class);

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");
		registerPage = registerPage.register(activeUser);

		System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		String actualErrorMsg = registerPage.getRegisterErrorMessage();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC08() {
		System.out.println("Prepare data");	
		String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
		String expectedPwdErrorMsg = "Invalid password length";
		String expectedPidErrorMsg = "Invalid ID length";

		System.out.println("TC08 - User can't create account while password and PID fields are empty");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Pre-condition: Get valid email");
		String email = TestUtils.getQuickEmail();
	    User invalidUser = new User(email, "", "", "");
		
		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoPage(MenuTab.REGISTER, RegisterPage.class);

		System.out.println("3. Enter valid email address and leave other fields empty");
		System.out.println("4. Click on \"Register\" button");
		registerPage = registerPage.register(invalidUser);

		System.out.println(
				"VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		String actualErrorMsg = registerPage.getRegisterErrorMessage();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");

		System.out.println("VP: Next to password fields, error message \"Invalid password length.\" displays");
		String actualPwdErrorMsg = registerPage.getPwdErrorMsg();
		Assert.assertEquals(actualPwdErrorMsg, expectedPwdErrorMsg, "Error message is not displayed as expected");

		System.out.println("VP: Next to PID field, error message \"Invalid ID length.\" displays");
		String actualPidErrorMsg = registerPage.getPidErrorMsg();
		Assert.assertEquals(actualPidErrorMsg, expectedPidErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC09() {
		System.out.println("Prepare data");
		String expectedMsg = "Registration Confirmed! You can now log in to the site.";
		String expectedThankyouMsg = "Thank you for registering your account";

		System.out.println("TC09 - User create and activate account");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		String railwayWindow = Constant.WEBDRIVER.getWindowHandle();

		System.out.println(
				"VP: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		// Home page is shown
		Assert.assertTrue(homePage.isHomePageDisplayed(), "\"Home page is NOT display\"");
		// Create an account link is shown and navigates to Register page
		Assert.assertTrue(homePage.isCreateAccountLinkDisplayed(), "Create an account link is not displayed");
		Assert.assertTrue(homePage.getCreateAccountHref().contains("Register"),
				"Create an account link does not navigate to Register page");

		System.out.println("Pre-condtion: Get user");
		// Mở GuerrillaMail ở tab mới
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    String mailWindow = Constant.WEBDRIVER.getWindowHandle();
	 // Generate email by GuerrillaMail
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();

	    String username = Utilities.generateRandomUsername();
	    String email = mailPage.createEmailAndGetIt(username);
	    User newUser = new User(email, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");
		
		System.out.println("2. Click on \"Create an account\"");
		Constant.WEBDRIVER.switchTo().window(railwayWindow);
		RegisterPage registerPage = homePage.clickCreateAnAccount();

		System.out.println("VP: Register page is shown");
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register page is not display");

		System.out.println("3. Enter valid information into all fields");
		System.out.println("4. Click on \"Register\" button");
		registerPage = registerPage.register(newUser);

		System.out.println("VP: \"Thank you for registering your account\" is shown");
		String actualThankyouMsg = registerPage.getThankyouMsg();
		Assert.assertEquals(actualThankyouMsg, expectedThankyouMsg, "Thank you message is not displayed as expected");

		System.out.println(
				"5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
		System.out.println("6. Login to the mailbox");
		

		System.out.println(
				"7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("8. Click on the activate link");
		Constant.WEBDRIVER.switchTo().window(mailWindow);
		mailPage.openConfirmEmail();
		ProjectUtils.switchToLastWindow();
		String activationWindow = Constant.WEBDRIVER.getWindowHandle();

		// Đóng các tab không cần thiết
		// Đóng tab Mail
		Constant.WEBDRIVER.switchTo().window(mailWindow);
		Constant.WEBDRIVER.close();

		// Đóng tab Đăng ký
		Constant.WEBDRIVER.switchTo().window(railwayWindow);
		Constant.WEBDRIVER.close();
		
		Constant.WEBDRIVER.switchTo().window(activationWindow);

		System.out.println(
				"VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site.\" is shown");
		String actualMsg = registerPage.getRegisterSuccessMsg();
		Assert.assertEquals(actualMsg, expectedMsg, "Confirmation message is not displayed as expected");
	}
}
