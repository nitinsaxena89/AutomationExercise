package app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import app.framework.PageBase;

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

	private void fillTime(String TimeDuration) {
		wait.until(ExpectedConditions.visibilityOf(txtStartTime));
		txtStartTime.sendKeys(TimeDuration);
	}

	private void clickStart() {
		wait.until(ExpectedConditions.visibilityOf(btnStartTimer));
		btnStartTimer.click();
	}
}
