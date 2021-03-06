package com.ibm.mil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PopulatingDataTest {
	private static RemoteWebDriver driver;
	private static IosUtilities utils = new IosUtilities();
	private static SignInUtilities signInUtils = new SignInUtilities();
	public static final long WAIT_TIME = 5000;

	@Before
	public void setup() throws Exception {
		driver = (RemoteWebDriver) utils.iosTestSetupWebDriver(driver);
	}

	@Test
	public void testPopulatingDataToHealthKit() throws Exception {
		
		// to populate health kit data, we must login with user2 and password2 credentials
		// Store the username field
		List<WebElement> textFields = driver.findElements(By
			.className("UIATextField"));

		// Store the password field.
		List<WebElement> secureTextFields = driver.findElements(By
			.className("UIASecureTextField"));

		// Store the login button.
		List<WebElement> loginButton = driver.findElements(By
				.className("UIAButton"));
		
		// Click on the username text field and type user2
		WebElement first = textFields.get(0);
		first.click();
		first.sendKeys("user2");

		// Click on the password text field and type password2
		WebElement second = secureTextFields.get(0);
		second.click();
		second.sendKeys("password2");
		 
		// Click the login button
		WebElement login = loginButton.get(0);		
		//after logging in, we will receive a pop up telling us that we are going to import a lot of
		// data into our health kit. we want to allow this
		
		//make sure that the pop up appeared by checking that the title of the popup appeared
		WebElement populate_healthkit_title = utils.waitForElement(driver, "Populate HealthKit");
		Assert.assertNotNull("Title is not null so it exists", populate_healthkit_title);
		
		//click "OK" on the pop up
		WebElement ok = utils.waitForElement(driver, "OK");
		Assert.assertNotNull("ok exists, so we can click on it", ok);
		//find the buttons
		List<WebElement> buttons = driver.findElements(By
				.className("UIAButton"));
		Assert.assertEquals(3, buttons.size());
		
		driver.switchTo().alert().accept();
		
		// Wait for menu to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// now we must accept the permissions
		signInUtils.PermissionsUtility(driver);
	
		//wait for the home screen to appear
		By navTitle = By.linkText("name=Physio");
		WebElement nav_title = driver.findElement(navTitle);
		Assert.assertNotNull(
				"Nav title is the name of the home screen",
				nav_title);
		
		// Wait for the data to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//wait for the alert for populating HealthKit to disappear
		waitForElementToDisappear(driver, "Populating HealthKit...");
		
		//wait for the home screen to appear (ouch icon is on the home screen)
		utils.waitForElement(driver, "ouch icon");
		
		// Wait for the home page to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//try to find "---" which is what gets filled in for the tabs if no data is loaded
		// if the dashes aren't present then we successfully loaded the data into heathkit
		// if we do get dashes then something went wrong with importing the data
			List<WebElement> static_texts = driver.findElements(By
					.className("UIAStaticText"));
			//check the first tab, the heart rate
			WebElement heart_rate = static_texts.get(16);
			String heart_rate_text = heart_rate.getAttribute("name");
			//assert that we didn't get dashes
			Assert.assertNotEquals("The data is dashes, something went wrong with loading data", heart_rate_text, "---");
			
			//check the second tab, the weight
			WebElement weight_tab = static_texts.get(23);
			String weight_tab_text = weight_tab.getAttribute("name");
			//assert that we didn't get dashes
			Assert.assertNotEquals("The data is dashes, something went wrong with loading data", weight_tab_text, "---");
			
			//check the third tab, the steps
			WebElement steps_tab = static_texts.get(28);
			String steps_tab_text = steps_tab.getAttribute("name");
			//assert that we didn't get dashes
			Assert.assertNotEquals("The data is dashes, something went wrong with loading data", steps_tab_text, "---");
			//System.out.println("dashes text is" + steps_tab+ ", " + steps_tab_text + ", " + steps_tab.getAttribute("tree"));
			
	}
	
	public WebElement waitForElementToDisappear(WebDriver driver, String elementId) {
		int iterations = 20;
		WebElement elem = null;
		for (int i = 0 ; i < iterations ; ++i ) {
			try {
				elem = driver.findElement(By.id(elementId));
				System.out.println("elementid: " + elementId + ", is still visible");
				if (elem == null) {
					break;
				}
				else {
					Thread.sleep(WAIT_TIME);
				}
			} catch (Exception ex) {
				
			}
		}
		return elem;
	}
	
	
	@After
	public void stop() {
		// Close the session
		utils.iosTestTeardownDriver(driver);
	}
}