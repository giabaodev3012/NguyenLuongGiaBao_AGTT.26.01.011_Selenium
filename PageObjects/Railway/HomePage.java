package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.Constant;

public class HomePage extends BasePage {
	// Locators
	private final By txtWelcome = By.xpath("//h1[.=\"Welcome to Safe Railway\"]");
	private final By lnkCreateAccount = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");
	
	// Elements
	public WebElement getLnkCreateAccount() {
		return ProjectUtils.findElement(lnkCreateAccount);
	}

	// Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}

	public boolean isHomePageDisplayed() {
		WaitUtils.waitForVisible(txtWelcome);
		return ProjectUtils.isElementDisplayed(txtWelcome);
	}
	
	public RegisterPage clickCreateAnAccount() {
		WaitUtils.waitForClickable(lnkCreateAccount);
		ProjectUtils.scrollDownByElement(getLnkCreateAccount());
		getLnkCreateAccount().click();
		
		return new RegisterPage();
	}
	
}
