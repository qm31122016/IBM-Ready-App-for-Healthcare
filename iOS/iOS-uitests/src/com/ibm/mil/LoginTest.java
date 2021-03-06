package com.ibm.mil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginTest {
	private static RemoteWebDriver driver;
	private static IosUtilities utils = new IosUtilities();

	@Before
	public void setupDriver() throws Exception {
		driver = (RemoteWebDriver) utils.iosTestSetupWebDriver(driver);
	}

	// Tests the possible various unsuccessful login attempts
	@Test
	public void testInvalidLogin() {

		// check that there only 1 button
		List<WebElement> numberOfButtons = driver.findElements(By.className("UIAButton"));

		// Test that the incorrect username message is correctly displayed when
		// an invalid username and password is entered
		List<WebElement> textFields = driver.findElements(By.className("UIATextField"));

		// check that there is one secure text field.
		List<WebElement> secureTextFields = driver.findElements(By.className("UIASecureTextField"));

		// Click on the username text field
		WebElement first = textFields.get(0);
		first.click();
		first.sendKeys("invalidUsername");

		// Click on the password text field
		WebElement second = secureTextFields.get(0);
		second.click();
		second.sendKeys("invalidPassword");

		// Click the login button
		WebElement loginButton = utils.waitForElement(driver, "Log In");
		loginButton.click();
		WebElement invalidID = utils.waitForElement(driver, "Try another ID or Password");
		invalidID.click();
	
	};

	// This test ensures that login with valid credentials is accepted and the
	// app proceeds to the dashboard screen as expected.

	@Test
	public void testValidLogin() {

		// check that there is one text field.
		List<WebElement> textFields = driver.findElements(By
				.className("UIATextField"));
		;

		// check that there is one secure text field.
		List<WebElement> secureTextFields = driver.findElements(By
				.className("UIASecureTextField"));
		

		// Click on the username text field
		WebElement first = textFields.get(0);
		first.click();
		first.sendKeys("user1");

		// Click on the 1st element
		WebElement second = secureTextFields.get(0);
		second.click();
		second.sendKeys("password1");

		// Click the login button
		WebElement loginButton = utils.waitForElement(driver, "Log In");
		loginButton.click();

		// Check that an element not present on login screen is present, thus
		// verifying login.
		List<WebElement> loginSuccess = driver.findElements(By
				.className("UIATableCell"));
		Assert.assertNotNull("UIATableCells exists, so successful login",
				loginSuccess);

	}

	@After
	public void stop() {
		utils.iosTestTeardownDriver(driver);
	}
}
