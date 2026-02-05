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
	public <T> T login(User user, Class<T> expectedPage) {
		// Submit login credentials
		getTxtUsername().clear();
		this.getTxtUsername().sendKeys(user.getUsername());

		getTxtPassword().clear();
		this.getTxtPassword().sendKeys(user.getPassword());

		this.getBtnLogin().click();

		try {
			return expectedPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
