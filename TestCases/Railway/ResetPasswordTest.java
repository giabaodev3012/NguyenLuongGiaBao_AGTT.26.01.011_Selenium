package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.ProjectUtils;
import Common.TestUtils;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;
import GuerrillaMail.GuerrillaMailPage;

public class ResetPasswordTest extends TestBase {

	@Test
	public void TC10() {
		System.out.println("Prepare data");
		User activeUser = TestUtils.createActivatedAccount();
		String emailUsername = Utilities.getUsernameFromEmail(activeUser.getUsername());

		String expectedErrorMsg = "The new password cannot be the same with the current password";

		System.out.println("TC10 - Reset password shows error if the new password is same as current");

		System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println("2. Click on \"Forgot Password page\" link");
		ForgotPasswordPage forgotPage = loginPage.clickForgotPwd();

		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		forgotPage.resetPassword(activeUser.getUsername());

		System.out.println("5. Login to the mailbox (the same mailbox when creating account)");
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.setMailboxUsername(emailUsername);

		System.out.println(
				"6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		mailPage.openResetPwdEmail();
		ProjectUtils.switchToLastWindow();

		System.out.println(
				"VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		PasswordResetPage resetPage = new PasswordResetPage();
		Assert.assertTrue(resetPage.isPasswordChangeFormDisplayed());

		System.out.println("8. Input same password into 2 fields \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		resetPage.inputNewPassword(Constant.PASSWORD, Constant.CONFIRMPASSWORD);

		System.out.println("VP: Message \"The new password cannot be the same with the current password\" is shown");
		String actualErrorMsg = resetPage.getResetPasswordMsg();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC11() {
		System.out.println("Prepare data");
		User activeUser = TestUtils.createActivatedAccount();
		String emailUsername = Utilities.getUsernameFromEmail(activeUser.getUsername());
		
		String expectedErrorMsg = "Could not reset password. Please correct the errors and try again.";
		String expectedPwdErrorMsg = "The password confirmation did not match the new password.";
		
		System.out.println("TC11 - Reset password shows error if the new password and confirm password doesn't match");

		System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);

		System.out.println("2. Click on \"Forgot Password page\" link");
		ForgotPasswordPage forgotPage = loginPage.clickForgotPwd();

		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		forgotPage.resetPassword(activeUser.getUsername());

		System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.setMailboxUsername(emailUsername);

		System.out.println(
				"6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		mailPage.openResetPwdEmail();
		ProjectUtils.switchToLastWindow();
		
		System.out.println(
				"VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		PasswordResetPage resetPage = new PasswordResetPage();
		Assert.assertTrue(resetPage.isPasswordChangeFormDisplayed());
		
		System.out.println("8. Input different input into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		resetPage.inputNewPassword(Constant.PASSWORD, "1234");

		System.out.println(
				"VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
		String actualErrorMsg = resetPage.getResetPasswordMsg();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
		
		System.out.println(
				"VP: Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		String actualPwdErrorMsg = resetPage.getPasswordErrorMsg();
		Assert.assertEquals(actualPwdErrorMsg, expectedPwdErrorMsg, "Error message is not displayed as expected");
	}

}
