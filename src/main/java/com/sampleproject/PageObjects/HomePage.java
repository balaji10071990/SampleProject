package com.sampleproject.PageObjects;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.sampleproject.utilities.*;

public class HomePage extends BasePage {

	@FindBy(xpath = "//*[@id='twotabsearchtextbox']")
	WebElement searchTextBox;

	@FindBy(xpath = "//*[@id='nav-search-submit-text']/following-sibling::input")
	WebElement searchSubmit;
	
	@FindBy(xpath = "//*[@id='nav-cart-count']")
	WebElement cart;
	

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(searchTextBox);
	}

	public HomePage open(Hashtable<String, String> data) {

		DriverManager.getDriver().navigate().to(data.get("url"));
		System.out.println("Page Opened");
		return (HomePage) openPage(HomePage.class);
	}

	public SearchResultsPage searchProduct(Hashtable<String,String> data) throws Exception {
		
		type(searchTextBox,data.get("searchKeyword"),"searchTextBox");
		click(searchSubmit,"searchSubmit");
		return (SearchResultsPage) openPage(SearchResultsPage.class);
	}

	
	public ShoppingCartPage goToShoppingCartPage() {
		
		click(cart,"cart");
		return (ShoppingCartPage) openPage(ShoppingCartPage.class);
		
	}
}
