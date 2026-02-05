package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtils;
import Common.ProjectUtils;
import Common.WaitUtils;
import DataObjects.User;

public class RegisterPage extends GeneralPage {

	// Locator
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPid = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@value='Register']");
	private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");

	// Element
	public WebElement getTxtEmail() {
		return ProjectUtils.findElement(txtEmail);
	}

	public WebElement getTxtPassword() {
		return ProjectUtils.findElement(txtPassword);
	}

	public WebElement getTxtConfirmPassword() {
		return ProjectUtils.findElement(txtConfirmPassword);
	}

	public WebElement getTxtPid() {
		return ProjectUtils.findElement(txtPid);
	}
	
	public WebElement getLblRegisterErrorMsg() {
		return ProjectUtils.findElement(lblRegisterErrorMsg);
	}

	// Method
	public RegisterPage register(User user) {
		// Submit register information
		WaitUtils.waitForVisible(txtEmail);
		getTxtEmail().sendKeys(user.getUsername());
		
		WaitUtils.waitForVisible(txtPassword);
		getTxtPassword().sendKeys(user.getPassword());
		
		WaitUtils.waitForVisible(txtConfirmPassword);
		this.getTxtConfirmPassword().sendKeys(user.getConfirmpassword());
		
		WaitUtils.waitForVisible(txtPid);
		getTxtPid().sendKeys(user.getPid());
		
		ActionUtils.scrollWaitAndClick(btnRegister);

		return this;
	}
	
	public String getRegisterErrorMessage() {
		WaitUtils.waitForVisible(lblRegisterErrorMsg);
		return getLblRegisterErrorMsg().getText();
	}

}
