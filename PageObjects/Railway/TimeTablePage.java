package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.Station;

public class TimeTablePage extends BasePage {
	// Locator
	private String lnkCheckPriceByStations = "//table[contains(@class,'MyTable')]//tr["
			+ "td[count(//tr[@class='TableSmallHeader']/th[normalize-space()='Depart Station']/preceding-sibling::th)+1][normalize-space()='%s']"
			+ " and "
			+ "td[count(//tr[@class='TableSmallHeader']/th[normalize-space()='Arrive Station']/preceding-sibling::th)+1][normalize-space()='%s']"
			+ "]//a[normalize-space()='check price']";
	
	private String lnkBookTicketByStations = "//table[contains(@class,'MyTable')]//tr["
			+ "td[count(//tr[@class='TableSmallHeader']/th[normalize-space()='Depart Station']/preceding-sibling::th)+1][normalize-space()='%s']"
			+ " and "
			+ "td[count(//tr[@class='TableSmallHeader']/th[normalize-space()='Arrive Station']/preceding-sibling::th)+1][normalize-space()='%s']"
			+ "]//a[normalize-space()='book ticket']";

	// Element

	// Method
	public TicketPricePage clickCheckPrice(Station departFrom, Station arriveAt) {
		By locator = By.xpath(String.format(lnkCheckPriceByStations, departFrom.getName(), arriveAt.getName()));
		WaitUtils.waitForClickable(locator);
		WebElement element = ProjectUtils.findElement(locator);
		ProjectUtils.scrollDownByElement(element);
		element.click();
		
		return new TicketPricePage();
	}
	
	public BookTicketPage clickBookTicket(Station departFrom, Station arriveAt) {
		By locator = By.xpath(String.format(lnkBookTicketByStations, departFrom.getName(), arriveAt.getName()));
		WaitUtils.waitForClickable(locator);
		WebElement element = ProjectUtils.findElement(locator);
		ProjectUtils.scrollDownByElement(element);
		element.click();
		
		return new BookTicketPage();
	}

}
