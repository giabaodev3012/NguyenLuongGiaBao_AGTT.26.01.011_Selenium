package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.Constant;

public class HomePage extends BasePage {
	// Locators
	private By txtWelcome = By.xpath("//h1[.=\"Welcome to Safe Railway\"]");
	private By lnkCreateAccount = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");

	// Elements
	public WebElement getLnkCreateAccount() {
		return ProjectUtils.findElement(lnkCreateAccount);
	}
	
	public WebElement getTxtWelcome() {
		return ProjectUtils.findElement(txtWelcome);
	}
	

	// Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}

	public boolean isHomePageDisplayed() {
		WaitUtils.waitForVisible(txtWelcome);
		ProjectUtils.scrollDownByElement(getTxtWelcome());
		return ProjectUtils.isElementDisplayed(txtWelcome);
	}

	public RegisterPage clickCreateAnAccount() {
		WaitUtils.waitForClickable(lnkCreateAccount);
		ProjectUtils.scrollDownByElement(getLnkCreateAccount());
		ProjectUtils.clickByJS(getLnkCreateAccount());

		return new RegisterPage();
	}

	public boolean isCreateAccountLinkDisplayed() {
		WaitUtils.waitForVisible(lnkCreateAccount);
		return ProjectUtils.isElementDisplayed(lnkCreateAccount);
	}

	public String getCreateAccountHref() {
		WaitUtils.waitForVisible(lnkCreateAccount);
		return getLnkCreateAccount().getAttribute("href");
	}

}
