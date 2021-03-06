package com.ibm.mil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.uiautomation.ios.client.uiamodels.impl.RemoteIOSDriver;

public class SignInUtilities {
	private static IosUtilities utils = new IosUtilities();
	
	//call if test needs to sign in AND allow all permissions
	public void SignInAndPermissionUtilities(WebDriver driver) throws Exception {
		// Store the username field
		List<WebElement> textFields = utils.waitForListElements(driver, "UIATextField", 10);

		// Store the password field.
		List<WebElement> secureTextFields = utils.waitForListElements(driver, "UIASecureTextField", 10);

		// Store the login button.
		

		// Click on the username text field and type user1
		WebElement first = textFields.get(0);
		first.click();
		first.sendKeys("user1");

		// Click on the password text field and type password1
		WebElement second = secureTextFields.get(0);
		second.click();
		second.sendKeys("password1");
 
		// Click the login button
		utils.closeKeyboard(driver);
		List<WebElement> loginButton = utils.waitForListElements(driver, "UIAButton",10);
		WebElement login = loginButton.get(0);
		//login.click();

		// Wait for next page to load, by waiting for nav bar title "Health Access"
		utils.waitForElement(driver, "Health Access", 10);
		//Lets attempt to drag the screen down a bit to expose all the items before we attempt to locate the switches.
		RemoteIOSDriver riosd = (RemoteIOSDriver) driver;
		//Dimension size = riosd.getScreenSize(); //here dimension contains the max horizontal and vertical pixel values.
		String jsScriptText = "UIATarget.localTarget().frontMostApp().mainWindow().tableViews()[0].scrollDown()";
		riosd.executeScript(jsScriptText, new Object[]{});

		//click allow on all of the slider tabs to be able to run other tests
				// get all of the element
		List<WebElement> allowSliders = utils.waitForListElements(driver,"UIASwitch",10);
		int reps= allowSliders.size();
		for (int i = 0 ; i < reps ; i++) {//WebElement elem : allowSliders) {
			allowSliders = utils.waitForListElements(driver,"UIASwitch",10);
			WebElement elem = allowSliders.get(i);
			try {
				elem.click();
			} catch (Exception ex) {
				System.out.println("elem: " + elem.getAttribute("tree"));
				throw (ex);
			}
		}
		
		//click the "done" button
		WebElement done = utils.waitForElement(driver, "Done", 10);
		done.click();
	}
	
	//use if test only needs to sign in 
	public void SignInUtility(WebDriver driver) throws Exception {
		// Store the username field
		List<WebElement> textFields = driver.findElements(By.className("UIATextField"));

		// Store the password field.
		List<WebElement> secureTextFields = driver.findElements(By.className("UIASecureTextField"));

		// Store the login button.
		List<WebElement> loginButton = driver.findElements(By.className("UIAButton"));

		// Click on the username text field and type user1
		WebElement first = textFields.get(0);
		first.click();
		first.sendKeys("user1");

		// Click on the password text field and type password1
		WebElement second = secureTextFields.get(0);
		second.click();
		second.sendKeys("password1");

		// Click the login button
		WebElement login = loginButton.get(0);
		login.click();
	}
	
	//use if only allow the permissions is needed
	public void PermissionsUtility (WebDriver driver) throws Exception {
		// Wait for next page to load, by waiting for nav bar title "Health Access"
		utils.waitForElement(driver, "Health Access", 10);
		
		//Lets scroll down to expose all the sliders, then lets click on them.
		RemoteIOSDriver riosd = (RemoteIOSDriver) driver;
		//Dimension size = riosd.getScreenSize(); //here dimension contains the max horizontal and vertical pixel values.
		String jsScriptText = "UIATarget.localTarget().frontMostApp().mainWindow().tableViews()[0].scrollDown()";
		riosd.executeScript(jsScriptText, new Object[]{});
		
		//click allow on all of the slider tabs to be able to run other tests
		// get all of the elements
		List<WebElement> allowSliders = driver.findElements(By.className("UIASwitch"));
		for (WebElement elem : allowSliders) {
			try {
				elem.click();
			} catch (Exception ex) {
				System.out.println("elem: " + elem.getAttribute("tree"));
				throw (ex);
			}
		}

		//click the "done" button
		WebElement done = utils.waitForElement(driver, "Done", 10);
		done.click();
	}
}
