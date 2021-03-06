package app.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.helpers.Constants;

public class PageBase {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;

	public PageBase(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.ELEMENT_WAIT_TIMEOUT_SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

}
