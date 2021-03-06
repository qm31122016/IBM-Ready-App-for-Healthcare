package com.ibm.mil;

import java.util.List;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ExerciseLibraryTest {
	private static RemoteWebDriver driver;
	private static IosUtilities utils = new IosUtilities();
	private static SignInUtilities signInUtils = new SignInUtilities();

	@Before
	public void setup() throws Exception {
		driver = (RemoteWebDriver) utils.iosTestSetupWebDriver(driver);
	}

	@Test
	public void testExerciseLibrary() throws Exception {

		// sign in and accept all permissions
		signInUtils.SignInAndPermissionUtilities(driver);

		// Select the menu button to reveal the menu
		WebElement menuButton = utils.waitForElement(driver, "menu icon");
		menuButton.click();


		// Save table cells in a list
		List<WebElement> tableCells = utils.waitForListElements(driver, "UIATableCell", 10);

		// click on the exercise library tab
		WebElement exerciseLibraryTab = tableCells.get(3);
		exerciseLibraryTab.click();

		// wait for the exercise library to load, which will have the "assigned"
		// exercise cell
		WebElement assignedExerciseCell = utils.waitForElement(driver,
				"Assigned");
		assignedExerciseCell.click();

		// TODO: as of now the number of exercises is hardcoded, as the app gets
		// more flushed out
		// this will have to change to adapt to the newer design

		WebElement routine1 = utils.waitForElement(driver, "Routine 1");
		routine1.click();

		WebElement exercise1 = utils.waitForElement(driver, "Exercise 1");
		exercise1.click();
		
		WebElement routine = utils.waitForElement(driver, "1 of 4");
		Assert.assertNotNull(
				"The text reads that it is on the first out of four routines, which means we are on the correct exercise",
				routine);
		
		WebElement next = utils.waitForElement(driver, "Next Exercise");
		next.click();

		WebElement start = utils.waitForElement(driver, "Start Next Exercise");
		start.click();

		routine = utils.waitForElement(driver, "2 of 4");
		Assert.assertNotNull(
				"The text reads that it is on the second out of four routines, which means we are on the correct exercise",
				routine);
		
		next = utils.waitForElement(driver, "Next Exercise");
		next.click();

		start = utils.waitForElement(driver, "Start Next Exercise");
		
		try {
			Thread.sleep(10500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		routine = utils.waitForElement(driver, "3 of 4");
		Assert.assertNotNull(
				"The text reads that it is on the third out of four routines, which means we are on the correct exercise",
				routine);
		
		next = utils.waitForElement(driver, "Next Exercise");
		next.click();
		
		start = utils.waitForElement(driver, "Start Next Exercise");
		start.click();
	
		routine = utils.waitForElement(driver, "4 of 4");
		Assert.assertNotNull(
				"The text reads that it is on the fourth out of four routines, which means we are on the correct exercise",
				routine);
		
		next = utils.waitForElement(driver, "Next Exercise");
		next.click();
		
		WebElement done = utils.waitForElement(driver, "Done");
		done.click();
		
		WebElement heart_tab = utils.waitForElement(driver, "heart_tab");
		Assert.assertNotNull("If the heart tab is visible we have confirmed the return to the dashboard", heart_tab);
		

	}
	

	@Test
	public void testEndRoutine() throws Exception {
		// sign in and accept all permissions
				signInUtils.SignInAndPermissionUtilities(driver);

				// Select the menu button to reveal the menu
				WebElement menuButton = utils.waitForElement(driver, "menu icon");
				menuButton.click();


				// Save table cells in a list
				List<WebElement> tableCells = utils.waitForListElements(driver, "UIATableCell", 10);

				// click on the exercise library tab
				WebElement exerciseLibraryTab = tableCells.get(3);
				exerciseLibraryTab.click();

				// wait for the exercise library to load, which will have the "assigned"
				// exercise cell
				WebElement assignedExerciseCell = utils.waitForElement(driver,
						"Assigned");
				assignedExerciseCell.click();

				WebElement routine1 = utils.waitForElement(driver, "Routine 1");
				routine1.click();

				WebElement exercise1 = utils.waitForElement(driver, "Exercise 1");
				exercise1.click();
				
				WebElement routine = utils.waitForElement(driver, "1 of 4");
				Assert.assertNotNull(
						"The text reads that it is on the first out of four routines, which means we are on the correct exercise",
						routine);
				WebElement endRoutine = utils.waitForElement(driver, "End Routine");
				endRoutine.click();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tableCells = utils.waitForListElements(driver, "UIATableCell", 10);
				int size = tableCells.size();
				for (int i=0;i<size;i++ ){
					tableCells.get(i).click();
				}
				
				WebElement finish = utils.waitForElement(driver, "Finish");
				finish.click();
				
			WebElement no =utils.waitForElement(driver, "No");
			no.click();
				
			WebElement back_button= utils.waitForElement(driver, "back button");
			back_button.click();
			
			WebElement next = utils.waitForElement(driver, "Next Exercise");
			next.click();
			
			WebElement start = utils.waitForElement(driver, "Start Next Exercise");
			start.click();
			
			endRoutine = utils.waitForElement(driver, "End Routine");
			endRoutine.click();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tableCells = utils.waitForListElements(driver, "UIATableCell", 10);
			size = tableCells.size();
			for (int i=0;i<size;i++ ){
				tableCells.get(i).click();
			}
			 finish = utils.waitForElement(driver, "Finish");
			finish.click();
			
		WebElement yes =utils.waitForElement(driver, "Yes");
		yes.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}


	@After
	public void stop() {
		// Close the session
		utils.iosTestTeardownDriver(driver);
	}
}