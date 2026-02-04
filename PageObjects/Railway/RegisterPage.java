package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import DataObjects.User;

public class RegisterPage extends GeneralPage {
	
	// Locator
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPid = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@value='Register']");
	
	// Element
	public WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(txtEmail);
	}
	
	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}
	
	public WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}
	
	public WebElement getTxtPid() {
		return Constant.WEBDRIVER.findElement(txtPid);
	}
	
	public WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(btnRegister);
	}
	

	// Method
	public void register(User user) {
		// Submit login credentials
		this.getTxtEmail().sendKeys(user.getUsername());
		this.getTxtPassword().sendKeys(user.getPassword());
		this.getTxtConfirmPassword().sendKeys(user.getConfirmpassword());
		this.getTxtPid().sendKeys(user.getPid());
		this.getBtnRegister().click();
	}
	
}
