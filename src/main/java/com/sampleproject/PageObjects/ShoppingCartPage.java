package com.sampleproject.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sampleproject.helper.assertion.AssertionHelper;

import static org.testng.Assert.assertEquals;

import java.util.Hashtable;

import org.apache.log4j.Logger;

public class ShoppingCartPage extends BasePage {

	@FindBy(xpath = "//*[@class='a-dropdown-label']")
	WebElement qtyDropdown;

	@FindBy(xpath = "//h2[contains(text(),'Shopping Cart')]")
	WebElement shoppingCartPageTitle;

	@FindBy(xpath = "(//*[contains(text(),'Delete')])[3]")
	WebElement delete;

	@FindBy(xpath = "//h1[contains(text(),'Your Shopping Cart is empty')]")
	WebElement CartIsEmpty;

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(shoppingCartPageTitle);

	}

	public void deleteProduct() throws InterruptedException {
		click(qtyDropdown, "Qty Dropdown");
		waitForElementToPresent(delete);
		click(delete, "delete");
		waitForElementToPresent(CartIsEmpty);		
		AssertionHelper.verifyText(CartIsEmpty.getText(), "Your Shopping Cart is empty");
		picture();
	}
}