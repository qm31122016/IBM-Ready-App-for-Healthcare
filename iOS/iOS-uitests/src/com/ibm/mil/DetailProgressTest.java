package com.ibm.mil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DetailProgressTest {
	private static RemoteWebDriver driver;
	private static IosUtilities utils = new IosUtilities();
	private static SignInUtilities signInUtils = new SignInUtilities();

	@Before
	public void setup() throws Exception {
		driver = (RemoteWebDriver) utils.iosTestSetupWebDriver(driver);
	}

	@Test
	public void testStepsView() throws Exception {

		// sign in and accept all permissions
		signInUtils.SignInAndPermissionUtilities(driver);

		// Wait for next page to load, aka wait for the ouch icon to appear
		utils.waitForElement(driver, "ouch icon");

		// Store the uiimages
		List<WebElement> uiImages = driver.findElements(By
				.className("UIAImage"));
		Assert.assertEquals(8, uiImages.size());

		// Select the menu button to reveal the menu
		WebElement menuButton = utils.waitForElement(driver, "menu icon");
		menuButton.click();

		// Wait for menu to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// click on the progressCell
		WebElement progressCell = utils.waitForElement(driver, "Progress");
		progressCell.click();

		// check that the week tab loaded
		WebElement weekTab = utils.waitForElement(driver, "Week");
		Assert.assertNotNull("weekTab exists", weekTab);
		weekTab.click();

		// check that the month tab loaded
		WebElement monthTab = utils.waitForElement(driver, "Month");
		Assert.assertNotNull("monthTab exists", monthTab);
		monthTab.click();

		// check that the year tab loaded
		WebElement yearTab = utils.waitForElement(driver, "Year");
		Assert.assertNotNull("yearTab exists", yearTab);
		yearTab.click();

		// Wait for next screen to load, aka wait for the day tab to be there
		// and check that it's not null
		WebElement dayTab = utils.waitForElement(driver, "Day");
		Assert.assertNotNull("dayTab exists", dayTab);
		dayTab.click();

		// verify that all of the selections are available
		// check for heart rate view
		WebElement heartRateView = utils.waitForElement(driver, "Heart Rate");
		Assert.assertNotNull("heart rate view exists", heartRateView);

		// check for weight view
		WebElement weightView = utils.waitForElement(driver, "Weight");
		Assert.assertNotNull("weight view exists", weightView);

		// check for steps view
		WebElement stepsView = utils.waitForElement(driver, "Steps");
		Assert.assertNotNull("steps view exists", stepsView);

		// check for calories view
		WebElement caloriesView = utils.waitForElement(driver, "Calories");
		Assert.assertNotNull("calories view exists", caloriesView);

		// check for pain view
		WebElement painView = utils.waitForElement(driver, "Pain");
		Assert.assertNotNull("pain view exists", painView);

		// click on the steps view
		stepsView.click();
		yearTab = utils.waitForElement(driver, "Year");
		yearTab.click();
		monthTab = utils.waitForElement(driver, "Month");
		monthTab.click();
		weekTab = utils.waitForElement(driver, "Week");
		weekTab.click();
		dayTab = utils.waitForElement(driver, "Day");
		dayTab.click();

		// Wait for next screen to load aka wait for Goal to load
		WebElement stepsGoal = utils.waitForElement(driver, "Goal");
		Assert.assertNotNull("Steps goal element is present", stepsGoal);

		// click on the "view all" button
		WebElement viewAll = utils.waitForElement(driver, "View All");
		Assert.assertNotNull("view all button is present", viewAll);
		viewAll.click();

		// Wait for view all page to load
		// verify that the heart rate view appears that was not on the detailed
		// steps screen
		heartRateView = utils.waitForElement(driver, "Heart Rate");
		Assert.assertNotNull("heart rate view exists", heartRateView);
		heartRateView.click();
		yearTab = utils.waitForElement(driver, "Year");
		yearTab.click();
		monthTab = utils.waitForElement(driver, "Month");
		monthTab.click();
		weekTab = utils.waitForElement(driver, "Week");
		weekTab.click();
		dayTab = utils.waitForElement(driver, "Day");
		dayTab.click();
		
		viewAll = utils.waitForElement(driver, "View All");
		Assert.assertNotNull("view all button is present", viewAll);
		viewAll.click();
		
		weightView = utils.waitForElement(driver, "Weight");
		Assert.assertNotNull("weight view exists", weightView);
		weightView.click();
		yearTab = utils.waitForElement(driver, "Year");
		yearTab.click();
		monthTab = utils.waitForElement(driver, "Month");
		monthTab.click();
		weekTab = utils.waitForElement(driver, "Week");
		weekTab.click();
		dayTab = utils.waitForElement(driver, "Day");
		dayTab.click();
		
		viewAll = utils.waitForElement(driver, "View All");
		Assert.assertNotNull("view all button is present", viewAll);
		viewAll.click();
		
		caloriesView = utils.waitForElement(driver, "Calories");
		Assert.assertNotNull("calories view exists", caloriesView);
		caloriesView.click();
		yearTab = utils.waitForElement(driver, "Year");
		yearTab.click();
		monthTab = utils.waitForElement(driver, "Month");
		monthTab.click();
		weekTab = utils.waitForElement(driver, "Week");
		weekTab.click();
		dayTab = utils.waitForElement(driver, "Day");
		dayTab.click();
		
		viewAll = utils.waitForElement(driver, "View All");
		Assert.assertNotNull("view all button is present", viewAll);
		viewAll.click();
		
		painView = utils.waitForElement(driver, "Pain");
		Assert.assertNotNull("pain view exists", painView);
		painView.click();
		yearTab = utils.waitForElement(driver, "Year");
		yearTab.click();
		monthTab = utils.waitForElement(driver, "Month");
		monthTab.click();
		weekTab = utils.waitForElement(driver, "Week");
		weekTab.click();
		dayTab = utils.waitForElement(driver, "Day");
		dayTab.click();
		
		viewAll = utils.waitForElement(driver, "View All");
		Assert.assertNotNull("view all button is present", viewAll);
		viewAll.click();
		

		
	}

	@After
	public void stop() {
		// Close the session
		utils.iosTestTeardownDriver(driver);
	}
}
