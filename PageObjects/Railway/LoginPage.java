package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import DataObjects.User;

public class LoginPage extends GeneralPage {

	// Locators
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

	// Elements
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(txtUsername);
	}

	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}

	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(btnLogin);
	}

	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}

	// Methods
	public HomePage login(User user) {
		// Submit login credentials
		this.getTxtUsername().sendKeys(user.getUsername());
		this.getTxtPassword().sendKeys(user.getPassword());
		this.getBtnLogin().click();

		// Land on Home page
		return new HomePage();
	}

	public String getLoginErrorMessage() {
		return this.getLblLoginErrorMsg().getText();
	}

	public void clearUsername() {
		getTxtUsername().clear();
	}

	public void clearPassword() {
		getTxtPassword().clear();
	}

}
