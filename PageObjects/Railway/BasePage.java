package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtils;
import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.MenuTab;

public abstract class BasePage {

	// Locators
	private static final String menuXpath = "//div[@id='menu']//a[.='%s']";
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

		ActionUtils.scrollWaitAndClick(target);

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getWelcomeMessage() {
		WaitUtils.waitForVisible(lblWelcomeMessage);
		return getLblWelcomeMessage().getText();
	}

	public boolean isLogoutTabUndisplayed() {
		return WaitUtils.waitForInvisible(getMenuLocator(MenuTab.LOGOUT.getText()));
	}

}
