package app.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.framework.PageBase;
import app.helpers.Constants;

public class CountdownPage extends PageBase {

	public CountdownPage(WebDriver driver) {
		super(driver);
	}

	@CacheLookup
	@FindBy(how = How.XPATH, using = ".//p[@class='ClassicTimer-time']")
	public WebElement dynLblTimer;

	public String getCountdownValue() {
		return dynLblTimer.getText();
	}

	public boolean isLoaded() {
		return dynLblTimer.isDisplayed();
	}

	public boolean isCountdownRunning(String CurrentTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Constants.COUNTDOWN_INTERVAL_SECONDS);
			wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(dynLblTimer, CurrentTime)));
			return true;
		} catch (UnhandledAlertException e) {
			return false;
		}
	}

	public void acceptTimeoutAlert() {
		driver.switchTo().alert().accept();
	}

	public boolean alertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

}
