package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtils;
import Common.ProjectUtils;
import Common.WaitUtils;
import DataObjects.User;

public class LoginPage extends BasePage {

	// Locators
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

	// Elements
	public WebElement getTxtUsername() {
		return ProjectUtils.findElement(txtUsername);
	}

	public WebElement getTxtPassword() {
		return ProjectUtils.findElement(txtPassword);
	}

	public WebElement getLblLoginErrorMsg() {
		return ProjectUtils.findElement(lblLoginErrorMsg);
	}

	// Methods
	public <T> T login(User user, Class<T> expectedPage) {
		// Submit login credentials
		WaitUtils.waitForVisible(txtUsername); // wait input visible rồi mới thao tác
		getTxtUsername().clear();
		getTxtUsername().sendKeys(user.getUsername());

		WaitUtils.waitForVisible(txtPassword);
		getTxtPassword().clear();
		getTxtPassword().sendKeys(user.getPassword());

		ActionUtils.scrollWaitAndClick(btnLogin);

		try {
			return expectedPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getLoginErrorMessage() {
		WaitUtils.waitForVisible(lblLoginErrorMsg);
		return getLblLoginErrorMsg().getText();
	}

	public void clearUsername() {
		getTxtUsername().clear();
	}

	public void clearPassword() {
		getTxtPassword().clear();
	}

}
