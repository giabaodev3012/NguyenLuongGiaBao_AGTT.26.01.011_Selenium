package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.MenuTab;

public abstract class BasePage {

	// Locators
	private static String menuXpath = "//div[@id='menu']//a[.='%s']";
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

	// Elements
	protected WebElement getLblWelcomeMessage() {
		return ProjectUtils.findElement(lblWelcomeMessage);
	}

	// Methods
	protected By getMenuLocator(String menuText) {
		return By.xpath(String.format(menuXpath, menuText));
	}

	public <T> T gotoPage(MenuTab tab, Class<T> pageClass) {
		By target = getMenuLocator(tab.getText());

		WaitUtils.waitForClickable(target);
		WebElement getMenuElement = ProjectUtils.findElement(target);
		ProjectUtils.scrollDownByElement(getMenuElement);
		getMenuElement.click();

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getWelcomeMessage() {
		WaitUtils.waitForVisible(lblWelcomeMessage);
		ProjectUtils.scrollDownByElement(getLblWelcomeMessage());
		return getLblWelcomeMessage().getText();
	}

	public boolean isLogoutTabDisappeared() {
		By logoutTab = getMenuLocator(MenuTab.LOGOUT.getText());
		WaitUtils.waitForInvisible(logoutTab);
		return !ProjectUtils.isElementDisplayed(logoutTab);
	}
}
