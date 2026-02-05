package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtils;
import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.MenuTab;

public abstract class GeneralPage {

	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By tabFAQ = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

	// Elements
	protected WebElement getLblWelcomeMessage() {
		return ProjectUtils.findElement(lblWelcomeMessage);
	}

	// Methods
	public <T> T gotoPage(MenuTab tab, Class<T> pageClass) {
		By target;
		
		switch (tab) {
		case LOGIN:
			target = tabLogin;
			break;
		case REGISTER:
			target = tabRegister;
			break;
		case FAQ:
			target = tabFAQ;
			break;
		case LOGOUT:
			target = tabLogout;
			break;
		default:
			throw new RuntimeException("Unsupported menu tab: " + tab);
		}
		
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
		return WaitUtils.waitForInvisible(tabLogout);
	}

}
