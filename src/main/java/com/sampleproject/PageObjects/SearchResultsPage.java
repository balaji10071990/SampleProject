package com.sampleproject.PageObjects;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class SearchResultsPage extends BasePage {

	@FindBy(xpath = "(//*[@class='a-link-normal a-text-normal'])[1]")
	WebElement firstProduct;
		



	// *[@class='row payby-cash']//button[contains(.,'Payment')]

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(firstProduct);
	}

	
	public ProductDetailsPage clickOnProduct() {
		updateAttribute("target", firstProduct, "_self");
		click(firstProduct, firstProduct.getText());
		return(ProductDetailsPage)openPage(ProductDetailsPage.class);
	}

	

}
