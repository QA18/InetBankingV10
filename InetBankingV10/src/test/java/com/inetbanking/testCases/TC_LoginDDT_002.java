package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{

	//Use dataProvider parameter in the @Test annotation to specify the @Dataprovider annotation name
	//Remember, here dataProvider is the part of @Test annotation method. It's not same as @DataProvider annotation
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException  //user name and pass will supply from @Dataprovider
	{
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(user);
		logger.info("User name provided");
		lp.setPassword(pwd);
		logger.info("Password provided");
		lp.clickSubmit();
		logger.info("Clicked on Submit");
		Thread.sleep(3000);
		
		//Check the alert is present or not
		//Note that if login is successful it means there is no alert
		
		if (isAlertPresent()==true) //Failure case
		{
			//Accept alert
			driver.switchTo().alert().accept();
			//come back to main window
			driver.switchTo().defaultContent();
			//If alert window is present then test case is fail
			Assert.assertTrue(false);
			logger.info("Login Failed");
			Thread.sleep(3000);
		}
		else //Passed case
		{
			//Will execute if login is successful. means no alert present
			Assert.assertTrue(true);
			logger.info("Login Success");
			//Click logout
			lp.clickLogout();
			logger.info("Successfully Logout");
			//Close Alert box of logout button
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
		}
	}
	
	//This method will check alert is present or not
	public boolean isAlertPresent()
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	//This method will read the data from excel 
	//and store it into the 2D array by using the data provider method
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		//Specify the path of the excel file
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		//count number of rows from the excel sheet getRowCount method
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		
		//count number of column from the excel sheet getCellCount method
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		//Create a 2D array to store data from excel sheet
		String logindata[][] = new String [rownum][colcount];
		
		//Create a nested for loop to read the data from excel sheet and store it in the array
		for (int i = 1; i<=rownum; i++)
		{
			for (int j=0; j<colcount; j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);//i=1, j=0 in present position
			}
		}
		return logindata;
	}
}
