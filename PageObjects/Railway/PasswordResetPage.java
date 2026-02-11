package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;

public class PasswordResetPage extends BasePage {

	// Locator
	private By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private By txtResetToken = By.xpath("//input[@id='resetToken']");
	private By btnResetPassword = By.xpath("//input[@value='Reset Password']");
	private By lblErrorMsg = By.xpath("(//div[@id='content']//p[contains(@class,'message')])");
	private By lblPwdErrorMsg = By.xpath("//label[@class='validation-error']");
			
	// Element
	private WebElement getTxtNewPassword() {
		return ProjectUtils.findElement(txtNewPassword);
	}

	private WebElement getTxtConfirmPassword() {
		return ProjectUtils.findElement(txtConfirmPassword);
	}

	private WebElement getBtnResetPassword() {
		return ProjectUtils.findElement(btnResetPassword);
	}

	private WebElement getLblErrorMsg() {
		return ProjectUtils.findElement(lblErrorMsg);
	}
	
	private WebElement getLblPwdErrorMsg() {
		return ProjectUtils.findElement(lblPwdErrorMsg);
	}

	protected WebElement getTxtResetToken() {
		return ProjectUtils.findElement(txtResetToken);
	}

	// Method
	public PasswordResetPage inputNewPassword(String pwd, String confirmpwd) {
		WaitUtils.waitForVisible(txtNewPassword);
		ProjectUtils.scrollDownByElement(getTxtNewPassword());
		getTxtNewPassword().sendKeys(pwd);

		WaitUtils.waitForVisible(txtConfirmPassword);
		ProjectUtils.scrollDownByElement(getTxtConfirmPassword());
		getTxtConfirmPassword().sendKeys(confirmpwd);

		WaitUtils.waitForClickable(btnResetPassword);
		ProjectUtils.scrollDownByElement(getBtnResetPassword());
		getBtnResetPassword().click();

		return this;
	}

	public String getResetPasswordMsg() {
		WaitUtils.waitForVisible(lblErrorMsg);
		ProjectUtils.scrollDownByElement(getLblErrorMsg());
		return getLblErrorMsg().getText();
	}
	
	public String getPasswordErrorMsg() {
		WaitUtils.waitForVisible(lblPwdErrorMsg);
		ProjectUtils.scrollDownByElement(getLblPwdErrorMsg());
		return getLblPwdErrorMsg().getText();
	}

	public boolean isPasswordChangeFormDisplayed() {
		return ProjectUtils.isElementDisplayed(txtNewPassword) && ProjectUtils.isElementDisplayed(txtConfirmPassword)
				&& ProjectUtils.isElementDisplayed(txtResetToken);
	}
}
