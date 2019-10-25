package com.sampleproject.PageObjects;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AddedToCartPage extends BasePage {

	@FindBy(xpath = "//*[@id='huc-v2-order-row-with-divider']//*[contains(text(),'Proceed to Buy')]")
	WebElement proceedtoBuy;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(proceedtoBuy);
	}

	

	public LoginPage clickOnproceedToBuy() {
		click(proceedtoBuy, "Proceed to Buy");
		return (LoginPage)openPage(LoginPage.class);		
	}

}