package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public abstract class GeneralPage {

	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

	// Elements
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}

	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}

	// Methods
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}

	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
	
	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}
}
