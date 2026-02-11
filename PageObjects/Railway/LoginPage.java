package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.ProjectUtils;
import Common.WaitUtils;
import DataObjects.User;

public class LoginPage extends BasePage {

	// Locators
	private By txtUsername = By.xpath("//input[@id='username']");
	private By txtPassword = By.xpath("//input[@id='password']");
	private By btnLogin = By.xpath("//input[@value='login']");
	private By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private By lnkForgotPwd = By.xpath("//a[contains(text(), \"Forgot Password\")]");
	
	// Elements
	private WebElement getTxtUsername() {
		return ProjectUtils.findElement(txtUsername);
	}

	private WebElement getBtnLogin() {
		return ProjectUtils.findElement(btnLogin);
	}

	private WebElement getTxtPassword() {
		return ProjectUtils.findElement(txtPassword);
	}

	private WebElement getLblLoginErrorMsg() {
		return ProjectUtils.findElement(lblLoginErrorMsg);
	}
	
	private WebElement getLnkForgotPwd() {
		return ProjectUtils.findElement(lnkForgotPwd);
	}

	// Methods
	public <T> T login(User user, Class<T> expectedPage) {
		// Username
		WaitUtils.waitForVisible(txtUsername);
		ProjectUtils.scrollDownByElement(getTxtUsername());
		getTxtUsername().clear();
		getTxtUsername().sendKeys(user.getUsername());

		// Password
		WaitUtils.waitForVisible(txtPassword);
		ProjectUtils.scrollDownByElement(getTxtPassword());
		getTxtPassword().clear();
		getTxtPassword().sendKeys(user.getPassword());

		// Login button
		WaitUtils.waitForClickable(btnLogin);
		ProjectUtils.scrollDownByElement(getBtnLogin());
		getBtnLogin().click();

		try {
			return expectedPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getLoginErrorMessage() {
		WaitUtils.waitForVisible(lblLoginErrorMsg);
		ProjectUtils.scrollDownByElement(getLblLoginErrorMsg());
		return getLblLoginErrorMsg().getText();
	}

	public void clearUsername() {
		WaitUtils.waitForVisible(txtUsername);
		ProjectUtils.scrollDownByElement(getTxtUsername());
		getTxtUsername().clear();
	}

	public void clearPassword() {
		WaitUtils.waitForVisible(txtPassword);
		ProjectUtils.scrollDownByElement(getTxtPassword());
		getTxtPassword().clear();
	}
	
	public ForgotPasswordPage clickForgotPwd() {
		WaitUtils.waitForClickable(lnkForgotPwd);
		ProjectUtils.scrollDownByElement(getLnkForgotPwd());
		getLnkForgotPwd().click();
		
		return new ForgotPasswordPage();
	}

}
