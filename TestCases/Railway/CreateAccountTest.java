package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;

public class CreateAccountTest extends TestBase {

	@Test
	public void TC07() {
		System.out.println("TC07 - User can't create account with an already in-use email");

		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// 2. Click on "Register" tab
		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoPage(MenuTab.REGISTER, RegisterPage.class);

		// 3. Enter information of the created account in Pre-condition
		// 4. Click on "Register" button
		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");

		User validUser = new User(Constant.USERNAME, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");
		RegisterPage afterRegisterPage = registerPage.register(validUser);

		// Verify error message
		System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		String expectedErrorMsg = "This email address is already in use.";
		String actualErrorMsg = afterRegisterPage.getRegisterErrorMessage();

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC08() {
		// 1. Navigate to QA Railway Website
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		// 2. Click on "Register" tab
		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoPage(MenuTab.REGISTER, RegisterPage.class);

		// 3. Enter valid email address and leave other fields empty
		// 4. Click on "Register" button
		System.out.println("3. Enter valid email address and leave other fields empty");
		System.out.println("4. Click on \"Register\" button");
		
		User invalidUser = new User(Constant.USERNAME, "", "", "");
		RegisterPage afterRegisterPage = registerPage.register(invalidUser);

		// Verify error message
		System.out.println(
				"VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
		String actualErrorMsg = afterRegisterPage.getRegisterErrorMessage();

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");

		// Verify password error message
		System.out.println("VP: Next to password fields, error message \"Invalid password length.\" displays");
		String expectedPwdErrorMsg = "Invalid password length";
		String actualPwdErrorMsg = afterRegisterPage.getPwdErrorMsg();

		Assert.assertEquals(actualPwdErrorMsg, expectedPwdErrorMsg, "Error message is not displayed as expected");

		// Verify pid error message
		System.out.println("VP: Next to PID field, error message \"Invalid ID length.\" displays");

		String expectedPidErrorMsg = "Invalid ID length";
		String actualPidErrorMsg = afterRegisterPage.getPidErrorMsg();

		Assert.assertEquals(actualPidErrorMsg, expectedPidErrorMsg, "Error message is not displayed as expected");
	}
}
