package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//This class will used to create Extent Report for the Project. 
//Use this java class in any project you need to create report. This is a part of framework design

//Inherit Reporting class to TestListenerAdapter
public class Reporting extends TestListenerAdapter{
	
	//Creating common variable 
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	//Creating a method to create a new report
	public void onStart(ITestContext textContext)
	{
		//Specify report time format by using SimpleDateFormat class
		String timeStamp = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		//Generating a new report name every time we run the test. It will be dynamic name every time
		String repName = "Test-Report-" + timeStamp + ".html";
		//Creating a new report
		htmlReporter = new ExtentHtmlReporter (System.getProperty("user.dir")+ "/test-output/" + repName); //Specify location
		
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml"); //Specify file path
		
		//Creating a new variable for ExtentReprots class
		extent = new ExtentReports();
		
		//Pass environment information
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Shafiq");
		
		//Others setting of the report like title, name, chart location, theme of the report
		htmlReporter.config().setDocumentTitle("IntelBanking Test Project"); //Title of the report
		htmlReporter.config().setReportName("Functional Test Report"); //name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	//Creating a method which will send the pass test message and what color will be the information front
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); //sent the passed information 
	}
	//Creating a method which will send the failure test message and will take a screenshot, color of the report
		public void onTestFailure(ITestResult tr)
		{
			logger=extent.createTest(tr.getName()); //create new entry in the report
			logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); //sent the failed information
			
			//capture the screen shot and create a variable to store the screenshot
			String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
			
			//Verify the screen shot is available or not
			File f = new File(screenshotPath);
			
			//Create a conditional statement if the screen shot is available then add it to the report
			//otherwise throw an exception
			if(f.exists())
			{
				try{
					logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath(screenshotPath));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	//Creating a method for test skipped
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	//Creating a method to flush the report after test done
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
