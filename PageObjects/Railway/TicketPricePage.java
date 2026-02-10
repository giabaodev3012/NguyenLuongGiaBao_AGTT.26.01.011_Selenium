package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.SeatType;

public class TicketPricePage extends BasePage {

	// Locator
	private By lblTitle = By.xpath("//div[@id='content']//h1");
	private By lblTicketPriceHeader = By.xpath("//tr[@class='TableSmallHeader']/th");
	private String priceBySeatType = "//tr[th[normalize-space()='Price (VND)']]/td["
			+ "count(//tr[th[normalize-space()='Seat type']]/td[normalize-space()='%s']/preceding-sibling::td) + 1"
			+ "]";

	// Element
	public WebElement getLblTitle() {
		return ProjectUtils.findElement(lblTitle);
	}

	public WebElement getLblTicketPriceHeader() {
		return ProjectUtils.findElement(lblTicketPriceHeader);
	}

	// Method
	public boolean isTicketPricePageDisplayed() {
		WaitUtils.waitForVisible(lblTitle);
		ProjectUtils.scrollDownByElement(getLblTitle());
		return ProjectUtils.isElementDisplayed(lblTitle);
	}

	public String getTicketPriceHeader() {
		WaitUtils.waitForVisible(lblTicketPriceHeader);
		ProjectUtils.scrollDownByElement(getLblTicketPriceHeader());
		return getLblTicketPriceHeader().getText();
	}
	
	public String getPriceBySeatType(SeatType seatType) {
		By cell = By.xpath(String.format(priceBySeatType, seatType.getCode()));
		WaitUtils.waitForVisible(cell);
		WebElement element = ProjectUtils.findElement(cell);
		ProjectUtils.scrollDownByElement(element);
		return element.getText();
	}

}
