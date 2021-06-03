package app.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import app.helpers.Constants;
import app.helpers.PropertyReader;

public class TestBase {
	private WebDriver driver;
	private PropertyReader config;

	@BeforeSuite
	public void beforeSuite() {
		config = new PropertyReader("appconfig.properties");
	}

	@BeforeClass
	@Parameters({ "browserType" })
	public void setup(String BrowserType) {
		try {
			createDriver(BrowserType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void cleanUp() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	private void createDriver(String BrowserType) {
		switch (BrowserType.toLowerCase()) {
		case "chrome": {
			driver = initializeChromeDriver();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + BrowserType);
		}
	}

	private WebDriver initializeChromeDriver() {
		System.setProperty("webdriver.chrome.driver", config.get("CHROME_DRIVER_PATH"));

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.get(config.get("BASE_URL"));
		return driver;
	}

}
