package com.sampleproject.testScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import com.sampleproject.ExtentListeners.ExtentListeners;
import com.sampleproject.utilities.*;

public class BaseTest {

	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	public boolean grid = false;
	private String defaultUserName;
	private String defaultPassword;
	private String saUserName;
	private String saPassword;
	private String ExplicitWait;
	
	
	
	public String getExplicitWait() {
		return ExplicitWait;
	}

	public void setExplicitWait(String ExplicitWait) {
		this.ExplicitWait = ExplicitWait;
	}

	public String getSaUserName() {
		return saUserName;
	}

	public void setSaUserName(String saUserName) {
		this.saUserName = saUserName;
	}

	public String getSaPassword() {
		return saPassword;
	}

	public void setSaPassword(String saPassword) {
		this.saPassword = saPassword;
	}

	public String getDefaultUserName() {
		return defaultUserName;
	}

	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	@BeforeSuite
	public void setUpFramework() {

		configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");

		if (System.getProperty("os.name").equalsIgnoreCase("mac")) {

			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");

		} else {

			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
			DriverFactory.setIeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");

		}
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config properties file loaded");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		;

	}

	public void logInfo(String message) {

		ExtentListeners.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties"
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void destroyFramework() {

	}

	public void openBrowser(String browser) {

		if (System.getenv("ExecutionType") != null && System.getenv("ExecutionType").equals("Grid")) {

			grid = true;
		}

		DriverFactory.setRemote(grid);
		Capabilities caps;
		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {

				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
				caps = ((RemoteWebDriver) driver).getCapabilities();
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}

		} else

		if (browser.equals("chrome")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}
		caps = ((RemoteWebDriver) driver).getCapabilities();
		System.out.println(driver);
		DriverCapabilities.setCapabilities(caps);
		String ID = driver.toString();
		ID = ID.replace("ChromeDriver: chrome on XP (","");
		ID = ID.replace(")", "");
		SessionID.setSessionID(ID);
		System.out.println(ID);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		JavaScript.setJavaScriptObject(js);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		waitHelper.setWebDriverWaitObject(wait);
			
		
		
		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		SessionId sessionid = ((RemoteWebDriver) driver).getSessionId();
//		System.out.println(sessionid);
//		System.out.println(DriverManager.getDriver().toString());
		
		setDefaultUserName(Config.getProperty("defaultUserName"));
		setDefaultPassword(Config.getProperty("defaultPassword"));
		
		
		
		setSaUserName(Config.getProperty("saUserName"));
		setSaPassword(Config.getProperty("saPassword"));
		setDefaultPassword(Config.getProperty("ExplicitWait"));

		
//		setPassword(Config.getProperty("password"));
		
	}

	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}




	
	public String currentTime() {

		DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
//		System.out.println(dateFormat.format(date)); //0809190355
		String d = dateFormat.format(date);		
		
		return d;

	}
			
	
	
	

}
