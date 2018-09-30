 package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	//Create a object for Properties file
	Properties pro;
	
	//Create a constructor of this class to load and read the config.properties file.
	public ReadConfig()
	{
		//Create a file object and specify the path of the properties file.
		File src = new File ("./Configuration/config.properties");
		
		try {
			FileInputStream	fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	//Create methods to get the value from the config.properties file
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	public String getUsername()
	{
		String username=pro.getProperty("username");
		return username;
	}
	public String getPassword()
	{
		String password=pro.getProperty("password");
		return password;
	}
	public String getChromePath()
	{
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	public String getIEPath()
	{
		String iepath=pro.getProperty("iepath");
		return iepath;
	}
	public String getFirefoxPath()
	{
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	public String getEdgePath()
	{
		String edgepath=pro.getProperty("edgepath");
		return edgepath;
	}
	

}