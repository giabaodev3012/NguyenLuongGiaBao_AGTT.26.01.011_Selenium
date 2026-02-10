package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.ProjectUtils;
import Common.WaitUtils;

public class BookTicketPage extends BasePage {

	// Locator
	private By ddlDepartDate = By.xpath("//select[@name='Date']");
	private By ddlDepartFrom = By.xpath("//select[@name='DepartStation']");
	private By ddlArriveAt = By.xpath("//select[@name='ArriveStation']");
	private By ddlSeatType = By.xpath("//select[@name='SeatType']");
	private By ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private By btnBookTicket = By.xpath("//input[@value='Book ticket']");

	// Element
	public WebElement getDdlDepartDate() {
		return ProjectUtils.findElement(ddlDepartDate);
	}

	public WebElement getDdlDepartFrom() {
		return ProjectUtils.findElement(ddlDepartFrom);
	}

	public WebElement getDdlArriveAt() {
		return ProjectUtils.findElement(ddlArriveAt);
	}

	public WebElement getDdlSeatType() {
		return ProjectUtils.findElement(ddlSeatType);
	}

	public WebElement getDdlTicketAmount() {
		return ProjectUtils.findElement(ddlTicketAmount);
	}

	public WebElement getBtnBookTicket() {
		return ProjectUtils.findElement(btnBookTicket);
	}

	// Method
	public String getSelectedDepartDate() {
		Select select = new Select(getDdlDepartDate());
		return select.getFirstSelectedOption().getText();
	}

	public BookTicketSuccessPage bookTicket(String targetDate, String departFrom, String arriveAt, String seatType,
			String amount) {

		// 1. Select Depart Date
		WaitUtils.waitForVisible(ddlDepartDate);
		ProjectUtils.scrollDownByElement(getDdlDepartDate());
		ProjectUtils.selectByVisibleText(ddlDepartDate, targetDate);

		// 2. Select Depart From
		WaitUtils.waitForVisible(ddlDepartFrom);
		ProjectUtils.scrollDownByElement(getDdlDepartFrom());
		ProjectUtils.selectByVisibleText(ddlDepartFrom, departFrom);

		WebElement oldArriveAt = ProjectUtils.findElement(ddlArriveAt);
		WaitUtils.waitUntilStale(oldArriveAt);

		// Select Arrive at
		WaitUtils.waitForVisible(ddlArriveAt);
		ProjectUtils.scrollDownByElement(getDdlArriveAt());
		ProjectUtils.selectByVisibleText(ddlArriveAt, arriveAt);

		// Select Seat type
		WaitUtils.waitForVisible(ddlSeatType);
		ProjectUtils.scrollDownByElement(getDdlSeatType());
		ProjectUtils.selectByVisibleText(ddlSeatType, seatType);

		// Select Ticket ammount
		WaitUtils.waitForVisible(ddlTicketAmount);
		ProjectUtils.scrollDownByElement(getDdlTicketAmount());
		ProjectUtils.selectByVisibleText(ddlTicketAmount, amount);

		// Click button
		WaitUtils.waitForClickable(btnBookTicket);
		ProjectUtils.scrollDownByElement(getBtnBookTicket());
		getBtnBookTicket().click();

		return new BookTicketSuccessPage();
	}
}
