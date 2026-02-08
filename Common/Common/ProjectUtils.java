package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

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
}
