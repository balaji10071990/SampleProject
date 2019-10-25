package com.sampleproject.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Hashtable;

import org.apache.log4j.Logger;



public class SelectDeliveryAddressPage extends BasePage {

	

	@FindBy(xpath = "(//a[contains(text(),'Deliver to this address')])[1]")
	WebElement deliverToThisAddress;

	

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(deliverToThisAddress);

	}

	public DeliveryOptionsPage clickOnDeliverToThisAddress() throws InterruptedException {
		click(deliverToThisAddress, "deliver To This Address");
		return(DeliveryOptionsPage) openPage(DeliveryOptionsPage.class);

		
	}

}