package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.TestUtils;
import Common.Utilities;
import Constant.BookTicketColumn;
import Constant.MenuTab;
import Constant.SeatType;
import Constant.Station;
import DataObjects.RouteInfo;
import DataObjects.TicketInfo;
import DataObjects.TicketPrice;
import DataObjects.User;

public class BookTicketTest extends TestBase {

	@Test
	public void TC12() {
		System.out.println("Prepare data");
		String expectedMsg = "Ticket booked successfully!";
		int plusDays = 2;

		System.out.println("TC12 - User can book 1 ticket at a time");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Pre-condition: Create Activate Account");
		User activeUser = TestUtils.createActivatedAccount();

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
		String targetDate = Utilities.plusDaysFromCurrentDepartDate(currentDepartDate, plusDays);

		TicketInfo ticket = new TicketInfo(targetDate, Station.NHATRANG.getName(), Station.HUE.getName(),
				SeatType.SBC.getDescription(), "1");
		BookTicketSuccessPage successPage = bookTicketPage.bookTicket(ticket);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		String actualMsg = successPage.getSuccessMsg();
		Assert.assertEquals(actualMsg, expectedMsg, "Successful message is not displayed as expected");

		System.out.println(
				"VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualDepartStation = successPage.getCellValueByHeader(BookTicketColumn.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, ticket.getDepartFrom());

		String actualArriveStation = successPage.getCellValueByHeader(BookTicketColumn.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, ticket.getArriveAt());

		String actualSeatType = successPage.getCellValueByHeader(BookTicketColumn.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, ticket.getSeatType());

		String actualAmount = successPage.getCellValueByHeader(BookTicketColumn.AMOUNT);
		Assert.assertEquals(actualAmount, ticket.getTicketAmount());

		String actualDepartDate = successPage.getCellValueByHeader(BookTicketColumn.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, ticket.getDepartDate());
	}

	@Test
	public void TC13() {
		System.out.println("Prepare data");
		String expectedMsg = "Ticket booked successfully!";
		int plusDays = 25;

		System.out.println("TC13 - User can book many tickets at a time");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Pre-condition: Create Activate Account");
		User activeUser = TestUtils.createActivatedAccount();

		System.out.println("2. Login with a valid account ");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);
		homePage = loginPage.login(activeUser, HomePage.class);

		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(MenuTab.BOOKTICKET, BookTicketPage.class);

		System.out.println("4. Select the next 25 days from \"Depart date\"");
		System.out.println("5. Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\"");
		System.out.println("6. Select \"Soft seat with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"5\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		String currentDepartDate = bookTicketPage.getSelectedDepartDate();
		String targetDate = Utilities.plusDaysFromCurrentDepartDate(currentDepartDate, plusDays);

		TicketInfo ticket = new TicketInfo(targetDate, Station.NHATRANG.getName(), Station.SAIGON.getName(),
				SeatType.SSC.getDescription(), "5");

		BookTicketSuccessPage successPage = bookTicketPage.bookTicket(ticket);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		String actualMsg = successPage.getSuccessMsg();
		Assert.assertEquals(actualMsg, expectedMsg, "Successful message is not displayed as expected");

		System.out.println(
				"VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualDepartStation = successPage.getCellValueByHeader(BookTicketColumn.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, ticket.getDepartFrom(), "Depart Station is incorrect");

		String actualArriveStation = successPage.getCellValueByHeader(BookTicketColumn.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, ticket.getArriveAt(), "Arrive Station is incorrect");

		String actualSeatType = successPage.getCellValueByHeader(BookTicketColumn.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, ticket.getSeatType(), "Seat Type is incorrect");

		String actualAmount = successPage.getCellValueByHeader(BookTicketColumn.AMOUNT);
		Assert.assertEquals(actualAmount, ticket.getTicketAmount(), "Ticket amount is incorrect");

		String actualDepartDate = successPage.getCellValueByHeader(BookTicketColumn.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, ticket.getDepartDate(), "Depart Date is incorrect");
	}

	@Test
	public void TC14() {
		System.out.println("Prepare data");
		TicketInfo ticket = new TicketInfo(Station.DANANG.getName(), Station.SAIGON.getName());
		String expectedTableHeader = "Ticket price from " + ticket.getDepartFrom() + " to " + ticket.getArriveAt();

		TicketPrice expectedPriceHS = new TicketPrice(SeatType.HS.getCode(), "310000");
		TicketPrice expectedPriceSS = new TicketPrice(SeatType.SS.getCode(), "335000");
		TicketPrice expectedPriceSSC = new TicketPrice(SeatType.SSC.getCode(), "360000");
		TicketPrice expectedPriceHB = new TicketPrice(SeatType.HB.getCode(), "410000");
		TicketPrice expectedPriceSB = new TicketPrice(SeatType.SB.getCode(), "460000");
		TicketPrice expectedPriceSBC = new TicketPrice(SeatType.SBC.getCode(), "510000");

		System.out.println("TC14 - User can check price of ticket from Timetable");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Pre-condition: Create Activate Account");
		User activeUser = TestUtils.createActivatedAccount();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);
		homePage = loginPage.login(activeUser, HomePage.class);

		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = homePage.gotoPage(MenuTab.TIMETABLE, TimeTablePage.class);

		System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		TicketPricePage ticketPricePage = timeTablePage.clickCheckPrice(ticket.getDepartFrom(), ticket.getArriveAt());

		System.out.println("VP: \"Ticket Price\" page is loaded");
		Assert.assertTrue(ticketPricePage.isTicketPricePageDisplayed(), "Ticket Price page is not loaded");

		System.out.println("VP: Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\"");
		String actualTableHeader = ticketPricePage.getTicketPriceHeader();
		Assert.assertEquals(actualTableHeader, expectedTableHeader, "Ticket price title is not correct");

		System.out.println("VP: Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		String actualPriceHS = ticketPricePage.getPriceBySeatType(SeatType.HS);
		Assert.assertEquals(actualPriceHS, expectedPriceHS.getPrice(), "Price of seat type is incorrect");

		String actualPriceSS = ticketPricePage.getPriceBySeatType(SeatType.SS);
		Assert.assertEquals(actualPriceSS, expectedPriceSS.getPrice(), "Price of seat type is incorrect");

		String actualPriceSSC = ticketPricePage.getPriceBySeatType(SeatType.SSC);
		Assert.assertEquals(actualPriceSSC, expectedPriceSSC.getPrice(), "Price of seat type is incorrect");

		String actualPriceHB = ticketPricePage.getPriceBySeatType(SeatType.HB);
		Assert.assertEquals(actualPriceHB, expectedPriceHB.getPrice(), "Price of seat type is incorrect");

		String actualPriceSB = ticketPricePage.getPriceBySeatType(SeatType.SB);
		Assert.assertEquals(actualPriceSB, expectedPriceSB.getPrice(), "Price of seat type is incorrect");

		String actualPriceSBC = ticketPricePage.getPriceBySeatType(SeatType.SBC);
		Assert.assertEquals(actualPriceSBC, expectedPriceSBC.getPrice(), "Price of seat type is incorrect");

	}

	@Test
	public void TC15() {
		System.out.println("Prepare data");
		RouteInfo route = new RouteInfo(Station.QUANGNGAI.getName(), Station.HUE.getName());
		String expectedMsg = "Ticket booked successfully!";
		int plusDays = 1;

		String today = Utilities.getToday();
		String targetDate = Utilities.plusDaysFromCurrentDepartDate(today, plusDays);

		System.out.println("TC15 - User can book ticket from Timetable");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Pre-condition: Create Activate Account");
		User activeUser = TestUtils.createActivatedAccount();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(MenuTab.LOGIN, LoginPage.class);
		homePage = loginPage.login(activeUser, HomePage.class);

		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = homePage.gotoPage(MenuTab.TIMETABLE, TimeTablePage.class);

		System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		BookTicketPage bookTicketPage = timeTablePage.clickBookTicket(route.getDepartFrom(), route.getArriveAt());

		System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		String actualDepartFrom = bookTicketPage.getSelectedDepartFrom();
		Assert.assertEquals(actualDepartFrom, route.getDepartFrom(), "Depart From is incorrect");

		String actualArriveAt = bookTicketPage.getSelectedArriveAt();
		Assert.assertEquals(actualArriveAt, route.getArriveAt(), "Arrive At is incorrect");

		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		System.out.println("7. Click on \"Book ticket\" button");
		TicketInfo ticket = new TicketInfo(targetDate, route.getDepartFrom(), route.getArriveAt(),
				SeatType.SSC.getDescription(), "5");
		BookTicketSuccessPage successPage = bookTicketPage.bookTicket(ticket);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays");
		String actualMsg = successPage.getSuccessMsg();
		Assert.assertEquals(actualMsg, expectedMsg, "Successful message is not displayed as expected");

		System.out.println(
				"VP: Information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualDepartStation = successPage.getCellValueByHeader(BookTicketColumn.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, ticket.getDepartFrom(), "Depart Station is incorrect");

		String actualArriveStation = successPage.getCellValueByHeader(BookTicketColumn.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, ticket.getArriveAt(), "Arrive Station is incorrect");

		String actualSeatType = successPage.getCellValueByHeader(BookTicketColumn.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, ticket.getSeatType(), "Seat Type is incorrect");

		String actualAmount = successPage.getCellValueByHeader(BookTicketColumn.AMOUNT);
		Assert.assertEquals(actualAmount, ticket.getTicketAmount(), "Ticket amount is incorrect");

		String actualDepartDate = successPage.getCellValueByHeader(BookTicketColumn.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, ticket.getDepartDate(), "Depart Date is incorrect");
	}

}
