package app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import app.framework.PageBase;

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
}
