package com.sampleproject.PageObjects;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sampleproject.helper.assertion.AssertionHelper;




public class LoginPage extends BasePage{
	

	

	
	@FindBy(xpath = "//*[@name='email']")
	WebElement emailOrPhNumber;

	@FindBy(xpath = "//*[@name='password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueBtn;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement loginBtn;
	
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
	 
		return ExpectedConditions.visibilityOf(emailOrPhNumber);
	}

	
	public SelectDeliveryAddressPage loginToApp(Hashtable<String,String> data) {
		type(emailOrPhNumber,data.get("emailOrPhNumber"),"login id");
		click(continueBtn,"continue Button");
		waitForElementToPresent(password);
		type(password,data.get("password"),"password");
		click(loginBtn,"login Button");
		return (SelectDeliveryAddressPage) openPage(SelectDeliveryAddressPage.class);
	}
	
}