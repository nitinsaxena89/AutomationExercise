package app.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import app.framework.TestBase;
import app.pages.CountdownPage;
import app.pages.HomePage;

public class TimerTest extends TestBase {

	@Test
	public void validateCountdown() {
		// Arrange
		String timeDuration = "25 seconds";
		HomePage homePage = new HomePage(getDriver());

		Assert.assertTrue(homePage.isLoaded(), "Verify Page is Loaded");

		// Act
		CountdownPage countdownPage = homePage.startCountdown(timeDuration);

		// Assert
		Assert.assertEquals(countdownPage.getCountdownValue(), timeDuration,
				"Verify Countdown started from time duration provided i.e" + timeDuration);

		String currentTime = timeDuration;
		int timeDigit = Integer.parseInt(timeDuration.split("\s")[0]); // Get Second Count

		while (countdownPage.isCountdownRunning(currentTime)) {
			currentTime = --timeDigit <= 1 ? timeDigit + " second" : timeDigit + " seconds";
			Assert.assertEquals(countdownPage.getCountdownValue(), currentTime,
					"Verify Countdown at second: " + currentTime);
		}
		
		Assert.assertEquals(countdownPage.getCountdownValue(), "Time Expired!",
				"Verify Time Expired is displayed after Countdown Ends");
	}

}
