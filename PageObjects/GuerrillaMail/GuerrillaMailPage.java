package GuerrillaMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ProjectUtils;
import Common.WaitUtils;
import Constant.Constant;
import Railway.BasePage;
import GuerrillaMail.GuerrillaMailPage;

public class GuerrillaMailPage extends BasePage {

	// Locator
	private By lblEmailHeader = By.xpath("//span[@id='inbox-id']");
	private By txtEmailHeader = By.xpath("//span[@id='inbox-id']//input[@type='text']");
	private By btnSet = By.xpath("//button[@class='save button small']");
	private By lblFullEmail = By.xpath("//span[@id='email-widget']");
	private By chkScramble = By.xpath("//span//input[@type='checkbox']");
	private By lblConfirmAccountMsg = By.xpath("//td[contains(text(), \"Please confirm your account\")]");
	private By lnkActivate = By.xpath("//div[@class='email_body']//a[contains(@href, 'Account/Confirm')]");

	// Element
	public GuerrillaMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLAMAIL_URL);
		return this;
	}

	public WebElement getLblEmailHeader() {
		return ProjectUtils.findElement(lblEmailHeader);
	}

	public WebElement getTxtEmailHeader() {
		return ProjectUtils.findElement(txtEmailHeader);
	}

	public WebElement getBtnSet() {
		return ProjectUtils.findElement(btnSet);
	}

	public WebElement getChkScramble() {
		return ProjectUtils.findElement(chkScramble);
	}

	public WebElement getLblFullEmail() {
		return ProjectUtils.findElement(lblFullEmail);
	}

	public WebElement getLblConfirmAccountMsg() {
		return ProjectUtils.findElement(lblConfirmAccountMsg);
	}

	public WebElement getLnkActivate() {
		return ProjectUtils.findElement(lnkActivate);
	}

	// Method
	public GuerrillaMailPage setMailboxUsername(String username) {
		WaitUtils.waitForClickable(lblEmailHeader);
		ProjectUtils.scrollDownByElement(getLblEmailHeader());
		getLblEmailHeader().click();

		WaitUtils.waitForVisible(txtEmailHeader);
		ProjectUtils.scrollDownByElement(getLblEmailHeader());
		getTxtEmailHeader().clear();
		getTxtEmailHeader().sendKeys(username);

		WaitUtils.waitForClickable(btnSet);
		ProjectUtils.scrollDownByElement(getBtnSet());
		getBtnSet().click();

		return this;
	}

	public GuerrillaMailPage uncheckScrambleAddress() {
		WaitUtils.waitForClickable(chkScramble);
		ProjectUtils.scrollDownByElement(getChkScramble());

		if (getChkScramble().isSelected()) {
			getChkScramble().click();
		}

		return this;
	}

	public String getFullEmail() {
		WaitUtils.waitForVisible(lblFullEmail);
		ProjectUtils.scrollDownByElement(getLblFullEmail());
		return getLblFullEmail().getText();
	}

	public String createEmailAndGetIt(String username) {
		setMailboxUsername(username);
		uncheckScrambleAddress();
		return getFullEmail();
	}

	public GuerrillaMailPage openConfirmEmail() {
		WaitUtils.waitForVisible(lblConfirmAccountMsg, 20);
		ProjectUtils.scrollDownByElement(getLblConfirmAccountMsg());
		getLblConfirmAccountMsg().click();

		return this;
	}

	public GuerrillaMailPage clickActivateLink() {
		WaitUtils.waitForVisible(lnkActivate);
		ProjectUtils.scrollDownByElement(getLnkActivate());
		getLnkActivate().click();

		return this;
	}

}
