package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;


public class BaseClass {

	//Create object of ReadConfig file
	ReadConfig readconfig = new ReadConfig();
	
		//Create Common Local Variable
		public String baseURL = readconfig.getApplicationURL();
		public String username = readconfig.getUsername();
		public String password = readconfig.getPassword();
		public static WebDriver driver;
		
		//Adding logger 
		public static Logger logger;
		
		@Parameters("browser")//to parameterization for run test in multiple browser
		@BeforeClass
		public void setup(String br)
		{
			//Adding logger and specify project name
			logger = Logger.getLogger("Inetbanking"); 
			//Specify log4 property file where the log file will be created
			PropertyConfigurator.configure("Log4j.properties");
			
			if (br.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
				driver = new ChromeDriver ();
			}
			else if(br.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
				driver = new FirefoxDriver ();
			}
			else if(br.equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
				driver = new InternetExplorerDriver ();
			}
			else if(br.equals("edge"))
			{
				System.setProperty("webdriver.ie.driver", readconfig.getEdgePath());
				driver = new InternetExplorerDriver ();
			}
		
			driver.get(baseURL);
						
			//Global implicit wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}
		
		@AfterClass
		public void tearDown()
		{
			driver.quit();
		}
		
		//Method for capture Screen Shot and pass two parameter for web driver and test name
		public void captureScreen (WebDriver driver, String tname) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
			FileUtils.copyFile(source, target);
			System.out.println("Screenshot taken");
		}
		
		public static String randomString (){
			String generatedString1 = RandomStringUtils.randomAlphabetic(5);
			return (generatedString1);
		}
		
		
		
}
