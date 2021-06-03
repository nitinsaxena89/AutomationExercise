package app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.framework.PageBase;
import app.helpers.Constants;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@CacheLookup
	@FindBy(how = How.ID, using = "EggTimer-start-time-input-text")
	private WebElement txtStartTime;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//button[text()='Start']")
	private WebElement btnStartTimer;

	public CountdownPage startCountdown(String TimeDuration) {
		fillTime(TimeDuration);
		clickStart();
		return new CountdownPage(driver);
	}
	
	public boolean isLoaded() {
		WebDriverWait pageLoadWait = new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT_SECONDS);
		return pageLoadWait.until(driver -> isPageLoadStateComplete());
	}

	private void fillTime(String TimeDuration) {
		wait.until(ExpectedConditions.visibilityOf(txtStartTime));
		txtStartTime.sendKeys(TimeDuration);
	}

	private void clickStart() {
		wait.until(ExpectedConditions.visibilityOf(btnStartTimer));
		btnStartTimer.click();
	}

	private boolean isPageLoadStateComplete() {
		return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
	}
}
