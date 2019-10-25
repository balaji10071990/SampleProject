package com.sampleproject.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sampleproject.helper.assertion.AssertionHelper;

public class PaymentmethodsPage extends BasePage {


	@FindBy(xpath = "//*[@value='SelectableAddCreditCard']")
	WebElement selectCreditOrDebitCard;

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(selectCreditOrDebitCard);
	}

	public void selectCreditOrDebitCard() {

		click(selectCreditOrDebitCard, "Credit/Debit Card");

	}

}
