package Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class WaitUtils {

	public static By waitForVisible(By locator) {
		return waitForVisible(locator, Constant.TIMEOUT);
	}

	public static By waitForVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return locator;
	}

	public static By waitForClickable(By locator) {
		return waitForClickable(locator, Constant.TIMEOUT);
	}

	public static By waitForClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return locator;
	}

	public static void waitForInvisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static void waitForInvisible(By locator) {
		waitForInvisible(locator, Constant.TIMEOUT);
	}

	public static void waitUntilStale(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	public static void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		wait.until(ExpectedConditions.alertIsPresent());
	}
}
