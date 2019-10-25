package com.sampleproject.testScripts;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.sampleproject.PageObjects.*;
import com.sampleproject.utilities.*;


public class TC001_Add_And_Pemove_Product extends BaseTest {

	
	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void TC001(Hashtable<String, String> data) throws Exception {

		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "TC001", data.get("Runmode"), excel);
		log.info("Inside Login Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : " + data.get("browser"));
		HomePage home = new HomePage().open(data);
		SearchResultsPage searchResultsPage =home.searchProduct(data);
		ProductDetailsPage productDetailsPage =searchResultsPage.clickOnProduct();
		AddedToCartPage addedToCartPage =productDetailsPage.addProductToCart();
		LoginPage loginPage =addedToCartPage.clickOnproceedToBuy();
		SelectDeliveryAddressPage selectDeliveryAddressPage = loginPage.loginToApp(data);
		DeliveryOptionsPage deliveryOptionsPage = selectDeliveryAddressPage.clickOnDeliverToThisAddress();
		PaymentmethodsPage paymentmethodsPage = deliveryOptionsPage.clickOncontinue();
		paymentmethodsPage.selectCreditOrDebitCard();
		home = new HomePage().open(data);
		ShoppingCartPage shoppingCartPage = home.goToShoppingCartPage();
		shoppingCartPage.deleteProduct();
		
		
	}

	@AfterMethod
	public void tearDown() {

		logInfo("TC001 Test Completed");

		quit();

	}

}
