package Railway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.WaitUtils;
import Constant.Constant;
import Common.ProjectUtils;

public class MyTicketPage extends BasePage {

	// Locator
	private String btnCancelByTicketId = "//input[@value='Cancel' and contains(@onclick,'DeleteTicket(%s)')]";

	// Element

	// Method
	public MyTicketPage acceptAlert() {
	   WaitUtils.waitForAlert();
	   Alert alert = Constant.WEBDRIVER.switchTo().alert();
	   alert.accept();
	   
	   return this;
	   
	}

	public void cancelTicketById(String ticketId) {
	    By locator = By.xpath(String.format(btnCancelByTicketId, ticketId));
	    WaitUtils.waitForClickable(locator);

	    WebElement btn = ProjectUtils.findElement(locator);
	    ProjectUtils.scrollDownByElement(btn);
	    btn.click();
	    acceptAlert();
	}
	
	public boolean isTicketDisplayedById(String ticketId) {
	    By locator = By.xpath(String.format(btnCancelByTicketId, ticketId));
	    return ProjectUtils.isElementDisplayed(locator);
	}



}
