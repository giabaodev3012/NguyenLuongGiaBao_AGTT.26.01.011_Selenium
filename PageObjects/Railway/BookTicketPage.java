package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.ProjectUtils;
import Common.WaitUtils;
import DataObjects.TicketInfo;

public class BookTicketPage extends BasePage {

	// Locator
	private By ddlDepartDate = By.xpath("//select[@name='Date']");
	private By ddlDepartFrom = By.xpath("//select[@name='DepartStation']");
	private By ddlArriveAt = By.xpath("//select[@name='ArriveStation']");
	private By ddlSeatType = By.xpath("//select[@name='SeatType']");
	private By ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private By btnBookTicket = By.xpath("//input[@value='Book ticket']");

	// Element
	private WebElement getDdlDepartDate() {
		return ProjectUtils.findElement(ddlDepartDate);
	}

	private WebElement getDdlDepartFrom() {
		return ProjectUtils.findElement(ddlDepartFrom);
	}

	private WebElement getDdlArriveAt() {
		return ProjectUtils.findElement(ddlArriveAt);
	}

	private WebElement getDdlSeatType() {
		return ProjectUtils.findElement(ddlSeatType);
	}

	private WebElement getDdlTicketAmount() {
		return ProjectUtils.findElement(ddlTicketAmount);
	}

	private WebElement getBtnBookTicket() {
		return ProjectUtils.findElement(btnBookTicket);
	}

	// Method
	public String getSelectedDepartDate() {
		Select select = new Select(getDdlDepartDate());
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedDepartFrom() {
		Select select = new Select(getDdlDepartFrom());
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedArriveAt() {
		Select select = new Select(getDdlArriveAt());
		return select.getFirstSelectedOption().getText();
	}
	

	public BookTicketSuccessPage bookTicket(TicketInfo ticket) {
		// 1. Select Depart Date
		WaitUtils.waitForVisible(ddlDepartDate);
		ProjectUtils.scrollDownByElement(getDdlDepartDate());
		ProjectUtils.selectByVisibleText(ddlDepartDate, ticket.getDepartDate());

		// 2. Select Depart From
		WaitUtils.waitForVisible(ddlDepartFrom);
		ProjectUtils.scrollDownByElement(getDdlDepartFrom());
		ProjectUtils.selectByVisibleText(ddlDepartFrom, ticket.getDepartFrom().getName());

		WebElement oldArriveAt = ProjectUtils.findElement(ddlArriveAt);
		WaitUtils.waitUntilStale(oldArriveAt);

		// Select Arrive at
		WaitUtils.waitForVisible(ddlArriveAt);
		ProjectUtils.scrollDownByElement(getDdlArriveAt());
		ProjectUtils.selectByVisibleText(ddlArriveAt, ticket.getArriveAt().getName());

		// Select Seat type
		WaitUtils.waitForVisible(ddlSeatType);
		ProjectUtils.scrollDownByElement(getDdlSeatType());
		ProjectUtils.selectByVisibleText(ddlSeatType, ticket.getSeatType().getDescription());

		// Select Ticket ammount
		WaitUtils.waitForVisible(ddlTicketAmount);
		ProjectUtils.scrollDownByElement(getDdlTicketAmount());
		ProjectUtils.selectByVisibleText(ddlTicketAmount, ticket.getTicketAmount());

		// Click button
		WaitUtils.waitForClickable(btnBookTicket);
		ProjectUtils.scrollDownByElement(getBtnBookTicket());
		getBtnBookTicket().click();

		return new BookTicketSuccessPage();
	}
}
