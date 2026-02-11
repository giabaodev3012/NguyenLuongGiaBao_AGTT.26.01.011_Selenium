package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.TestUtils;
import Common.Utilities;
import Constant.MenuTab;
import DataObjects.TicketInfo;
import DataObjects.User;

public class CancelBookingTest extends TestBase {

	@Test
	public void TC16() {
		System.out.println("TC16 - User can cancel a ticket");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Pre-condition: Create Activate Account");
		User activeUser = TestUtils.createActivatedAccount();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);
		homePage = loginPage.login(activeUser, HomePage.class);

		System.out.println("3. Book a ticket");
		BookTicketPage bookTicketPage = homePage.gotoPage(MenuTab.BOOKTICKET, BookTicketPage.class);

		String currentDepartDate = bookTicketPage.getSelectedDepartDate();
		String targetDate = Utilities.plusDaysFromCurrentDepartDate(currentDepartDate, 4);

		TicketInfo ticket = new TicketInfo(targetDate, "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");
		BookTicketSuccessPage successPage = bookTicketPage.bookTicket(ticket);
		String ticketId = successPage.getTicketIdFromUrl();

		System.out.println("4. Click on \"My ticket\" tab");
		MyTicketPage myTicketPage = successPage.gotoPage(MenuTab.MYTICKET, MyTicketPage.class);

		System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
		System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage.cancelTicketById(ticketId);

		System.out.println("VP: The canceled ticket is disappeared.");
		Assert.assertFalse(myTicketPage.isTicketDisplayedById(ticketId),
				"Ticket with id " + ticketId + " is still displayed after cancel.");
	}

}
