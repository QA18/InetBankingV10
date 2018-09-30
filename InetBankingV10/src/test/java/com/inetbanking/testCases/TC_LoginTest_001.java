package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException
	{
		//Create a object of LoginPage
		LoginPage lp = new LoginPage (driver);
		lp.setUserName(username);
		logger.info("User Name Entered");
		lp.setPassword(password);
		logger.info("Password Entered");
		lp.clickSubmit();
		logger.info("Clicked on Submit Button");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			logger.info("Login Test Passed");
			Assert.assertTrue(true);
		}
		else
		{
			//if the test is fail, capture screen shot
			captureScreen(driver, "loginTest");
			logger.info("Login Test Failed");
			Assert.assertTrue(false);
			
		}
		
	}
	
}
