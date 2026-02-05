package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ActionUtils {

	public static void scrollWaitAndClick(By locator) {
		// wait visible
		ProjectUtils.findElement(WaitUtils.waitForVisible(locator));

		// scroll
		WebElement element = ProjectUtils.findElement(locator);
		ProjectUtils.scrollDownByElement(element);

		// wait clickable + click
		ProjectUtils.findElement(WaitUtils.waitForClickable(locator)).click();
	}
}
