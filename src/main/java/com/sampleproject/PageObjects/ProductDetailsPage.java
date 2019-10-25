package com.sampleproject.PageObjects;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ProductDetailsPage extends BasePage {

	@FindBy(xpath = "//*[@id='add-to-cart-button']")
	WebElement addtoCart;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(addtoCart);
	}

		public AddedToCartPage addProductToCart() {
		click(addtoCart, "Add to Cart");
		return(AddedToCartPage) openPage(AddedToCartPage.class);
	}

	
}
