package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;

public class ForgotPasswordPage extends BasePage{
	
	// Locator
	private By txtEmail = By.xpath("//input[@id='email']");
	private By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
	
	// Element
	public WebElement getTxtEmail() {
		return ProjectUtils.findElement(txtEmail);
	}
	
	public WebElement getBtnSendInstructions() {
		return ProjectUtils.findElement(btnSendInstructions);
	}
	
	
	// Method
	public ForgotPasswordPage resetPassword(String email) {
		WaitUtils.waitForVisible(txtEmail);
		ProjectUtils.scrollDownByElement(getTxtEmail());
		getTxtEmail().sendKeys(email);
		
		WaitUtils.waitForVisible(btnSendInstructions);
		ProjectUtils.scrollDownByElement(getBtnSendInstructions());
		getBtnSendInstructions().click();
		
		return this;
	}

}
