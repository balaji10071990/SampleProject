package com.sampleproject.PageObjects;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DeliveryOptionsPage extends BasePage {

	@FindBy(xpath = "(//*[@class='a-button-text'])[1]")
	WebElement continueBtn;
	@FindBy(xpath = "//*[contains(text(),'Book Tickets')]")
	WebElement bookTicketsTab;
	@FindBy(xpath = "//select")
	WebElement selectTickets;
	@FindBy(xpath = "//h4[contains(.,'Event Attendees')]")
	WebElement attendeFormView;
	@FindBy(xpath = "//*[@name='FirstName[]']")
	WebElement FirstName;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(continueBtn);
	}

	

	public PaymentmethodsPage clickOncontinue() {
		click(continueBtn, "continue");
		return (PaymentmethodsPage)openPage(PaymentmethodsPage.class);		
	}

}
