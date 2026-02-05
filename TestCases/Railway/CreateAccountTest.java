package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.MenuTab;
import DataObjects.User;

public class CreateAccountTest extends GeneralTest {
	
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
		System.out.println("3. Enter information of the created account in Pre-condition and 4. Click on \"Register\" button");
		User validUser = new User(Constant.USERNAME, Constant.PASSWORD, Constant.CONFIRMPASSWORD, "12345678");
		RegisterPage afterRegisterPage = registerPage.register(validUser);
		
		
		// Verify error message
		String expectedErrorMsg = "This email address is already in use.";
		String actualErrorMsg = afterRegisterPage.getRegisterErrorMessage();
		
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");	
	}
}
