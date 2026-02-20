package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;

import Constant.Constant;

public class ProjectUtils {

	public static WebElement findElement(By locator) {
		return Constant.WEBDRIVER.findElement(locator);
	}

	public static void scrollDownByElement(WebElement element) {
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView({block:'center'});",
				element);
	}

	public static boolean isElementDisplayed(By locator) {
		try {
			return Constant.WEBDRIVER.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static void switchToLastWindow() {
		for (String windowHandle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(windowHandle);
		}
	}

	public static void selectByVisibleText(By locator, String text) {
		WebElement element = findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public static void clickByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].click();", element);
	}

	public static String openNewTab() {
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
		return Constant.WEBDRIVER.getWindowHandle();
	}

	public static void switchToWindow(String windowHandle) {
		Constant.WEBDRIVER.switchTo().window(windowHandle);
	}

	public static void closeAndSwitchTo(String windowHandle) {
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(windowHandle);
	}

}
