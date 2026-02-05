package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.MenuTab;

public abstract class GeneralPage {

	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By tabFAQ = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

	// Elements
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}

	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}

	protected WebElement getTabFAQ() {
		return Constant.WEBDRIVER.findElement(tabFAQ);
	}

	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}

	// Methods
	public <T> T gotoPage(MenuTab tab, Class<T> pageClass) {
		switch (tab) {
		case LOGIN:
			getTabLogin().click();
			break;
		case REGISTER:
			getTabRegister().click();
			break;
		case FAQ:
			getTabFAQ().click();
			break;
		case LOGOUT:
			getTabLogout().click();
			break;
		default:
			throw new RuntimeException("Unsupported menu tab: " + tab);
		}

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}

	public boolean isLogoutTabUndisplayed() {
		return Constant.WEBDRIVER.findElements(tabLogout).size() == 0;
	}

}
