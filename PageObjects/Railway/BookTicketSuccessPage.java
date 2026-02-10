package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;

public class BookTicketSuccessPage extends BasePage {

	// Locator
	private By txtSuccessMsg = By.xpath("//h1");
	private String cellValueByHeader = "//tr[@class='OddRow']/td["
			+ "count(//tr[@class='TableSmallHeader']/th[normalize-space()='%s']/preceding-sibling::th) + 1" + "]";

	// Element
	public WebElement getTxtSuccessMsg() {
		return ProjectUtils.findElement(txtSuccessMsg);
	}

	// Method
	public String getSuccessMsg() {
		WaitUtils.waitForVisible(txtSuccessMsg);
		ProjectUtils.scrollDownByElement(getTxtSuccessMsg());
		return getTxtSuccessMsg().getText();
	}

	public String getCellValueByHeader(String headerName) {
		By cell = By.xpath(String.format(cellValueByHeader, headerName));
		WaitUtils.waitForVisible(cell);
		return ProjectUtils.findElement(cell).getText();
	}

}
