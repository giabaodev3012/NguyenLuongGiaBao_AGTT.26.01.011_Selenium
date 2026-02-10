package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.TestUtils;
import Common.Utilities;
import Constant.MenuTab;
import DataObjects.User;

public class BookTicketTest extends TestBase {

	@Test
	public void TC12() {
		System.out.println("Prepare data");
		User activeUser = TestUtils.createActivatedAccount();
		String departFrom = "Nha Trang";
		String arriveAt = "Huế";
		String seatType = "Soft bed with air conditioner";
		String ticketAmount = "1";

		String expectedMsg = "Ticket booked successfully!";

		System.out.println("TC12 - User can book 1 ticket at a time");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account ");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);
		homePage = loginPage.login(activeUser, HomePage.class);

		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(MenuTab.BOOKTICKET, BookTicketPage.class);

		System.out.println("4. Select the next 2 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"1\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		String currentDepartDate = bookTicketPage.getSelectedDepartDate();
		String targetDate = Utilities.plusDaysFromCurrentDepartDate(currentDepartDate, 2);

		BookTicketSuccessPage successPage = bookTicketPage.bookTicket(targetDate, departFrom, arriveAt, seatType, ticketAmount);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		String actualMsg = successPage.getSuccessMsg();
		Assert.assertEquals(actualMsg, expectedMsg, "Successful message is not displayed as expected");

		System.out.println(
				"VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualDepartStation = successPage.getCellValueByHeader("Depart Station");
		Assert.assertEquals(actualDepartStation, departFrom);

		String actualArriveStation = successPage.getCellValueByHeader("Arrive Station");
		Assert.assertEquals(actualArriveStation, arriveAt);

		String actualSeatType = successPage.getCellValueByHeader("Seat Type");
		Assert.assertEquals(actualSeatType, seatType);

		String actualAmount = successPage.getCellValueByHeader("Amount");
		Assert.assertEquals(actualAmount, ticketAmount);

		String actualDepartDate = successPage.getCellValueByHeader("Depart Date");
		Assert.assertEquals(actualDepartDate, targetDate);
	}

}
