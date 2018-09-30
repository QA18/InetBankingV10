package com.inetbanking.testCases;

import org.testng.annotations.Test;
import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	@Test
	public void addNewCustomer() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		logger.info("Login is Completed");
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage (driver);
		
		addcust.clickAddNewCustomer();
		logger.info("Providing customer details............");
		addcust.custName("Shafiq");
		addcust.custgender("male");
		addcust.custdob("10", "23", "1989");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		logger.info("City entered");
		addcust.custstate("AP");
		addcust.custpinno("234230");
		addcust.custtelephoneno("284338989090");
		String email = randomString()+"@gmail.com"; 
		addcust.custemailid(email);
		logger.info("email address entered");
		addcust.custpassword("a1b2c3");
		logger.info("Password entered");
		addcust.custsubmit();
		logger.info("Validating adding new customer .........");
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
	}
}
