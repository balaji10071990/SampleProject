package com.sampleproject.PageObjects;

import java.awt.Robot;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.sampleproject.ExtentListeners.ExtentListeners;
import com.sampleproject.ExtentListeners.ExtentManager;
import com.sampleproject.utilities.*;



public abstract class BasePage<T> {

	protected WebDriver driver;
	protected String ID;
	public Logger log = Logger.getLogger(BasePage.class);
	// private long LOAD_TIMEOUT = 200;
	private int AJAX_ELEMENT_TIMEOUT = 150;
	public int expTime = 60;
	protected JavascriptExecutor exe;
	protected Robot robot;
	protected WebDriverWait wait;
	protected Capabilities caps;

	public BasePage() {
		this.driver = DriverManager.getDriver();
		this.ID = SessionID.getSessionID();
		this.exe = JavaScript.getJavaScriptObject();	
		this.wait = waitHelper.getWebDriverWaitObject();
		this.caps = DriverCapabilities.getCapabilities();
	}

	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = PageFactory.initElements(driver, clazz);
			PageFactory.initElements(ajaxElemFactory, page);
			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
			waitForPageToLoad(pageLoadCondition);
			picture();
		} catch (NoSuchElementException e) {
			/*
			 * String error_screenshot = System.getProperty("user.dir") +
			 * "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
			 * this.takeScreenShot(error_screenshot);
			 */ throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}
	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		// WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		wait.until(pageLoadCondition);
	}
	protected abstract ExpectedCondition getPageLoadCondition();
	protected void waitForElementToPresent(WebElement element) {

		log.info("waiting for :" + element.toString() + " for :" + expTime + " seconds");
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void click(WebElement element, String elementName) {

		ExtentListeners.testReport.get().info("Clicking on : " + elementName);
		// System.out.println("Clicking on : "+elementName);
		log.info("Clicking on : " + elementName);
		highlightElement(element);
		element.click();
	}

	public void type(WebElement element, String value, String elementName) {
		// System.out.println("Typing in : "+elementName+" entered the value as :
		// "+value);
		log.info("Typing in : " + elementName + " entered the value as : " + value);
		ExtentListeners.testReport.get().info("Typing in : " + elementName + " entered the value as : " + value);
		element.clear();
		highlightElement(element);
		element.sendKeys(value);
	}
	public void picture() {
		try {
			ExtentManager.captureScreenshot();
			ExtentListeners.testReport.get().info(
					"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		} catch (Exception e) {

		}
	}

	// *******************************************JavaScript*********************************//

	public void highlightElement(WebElement element) {
		exe.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	public void updateAttribute(String attribute, WebElement element, String value) {
		exe.executeScript("arguments[0].setAttribute('"+attribute+"','" + value + "');", element);
	}

}