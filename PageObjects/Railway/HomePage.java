package Railway;

import org.openqa.selenium.By;

import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.Constant;

public class HomePage extends BasePage {
	// Locators
	private final By txtWelcome = By.xpath("//h1[.=\"Welcome to Safe Railway\"]");

	// Elements

	// Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}

	public boolean isHomePageDisplayed() {
		WaitUtils.waitForVisible(txtWelcome);
		return ProjectUtils.isElementDisplayed(txtWelcome);
	}
}
