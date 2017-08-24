package com.Cognizant.C2.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FunctionLibrary {

	public static void browserOpen() throws Exception
	{
		String browserName=ExceDataFetch("Login",1,3);
		String URL=ExceDataFetch("Login",1,0);
		int timeOut=Integer.parseInt(ExceDataFetch("Login",1,4));
		String location=ExceDataFetch("Login",1,5);
		if(location.equals("Local"))
		{
			switch(browserName)
			{
				case "IE":
					System.setProperty("webdriver.ie.driver", "..\\Cognizant20\\lib\\IEDriverServer_Win32_2.53.0\\IEDriverServer.exe");
					GlobalVariable.myDriver = new InternetExplorerDriver();
					break;
				case "Chrome":
					System.setProperty("webdriver.chrome.driver", "..\\Cognizant20\\lib\\chromedriver_win32\\chromedriver.exe");
					GlobalVariable.myDriver = new ChromeDriver();
					break;
				case "Firefox":
					GlobalVariable.myDriver = new FirefoxDriver();
					break;
				default:
					System.out.println("Please Enter a Valid Browser Name");
					System.exit(0);			
			}
		}
		else if(location.equals("Remote"))
		{
			switch(browserName)
			{
				case "IE":
					DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
					cap.setPlatform(Platform.WINDOWS);
					URL url = new URL("http://10.245.188.178:4444/wd/hub");
					GlobalVariable.myDriver=new RemoteWebDriver(url,cap);
					break;	
				default:
					System.out.println("Please Enter a Valid Browser Name");
					System.exit(0);			
			}
		}
		
		GlobalVariable.myDriver.manage().window().maximize();
		GlobalVariable.myDriver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		GlobalVariable.myDriver.get(URL);
		Thread.sleep(3000);	
	}
	
	public static void ExcelOpen() throws IOException
	{
		File file = new File("..\\Cognizant20\\Test Data\\Details.xls");
		FileInputStream fis = new FileInputStream(file);
		GlobalVariable.wb = new HSSFWorkbook(fis);
	}
	
	public static String ExceDataFetch(String sheetName,int row,int col) throws IOException
	{
		ExcelOpen();
		String Data= GlobalVariable.wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
		
		ExcelClose();
		return Data;
	}
	
	public static void ExcelClose() throws IOException
	{
		//File file = new File("..\\Cognizant20\\Test Data\\Details.xls");
		//FileOutputStream fos = new FileOutputStream(file);
		//GlobalVariable.wb.write(fos);
		GlobalVariable.wb.close();	
	}
	
	public static void MouseHover(WebElement el) throws Exception
	{
		Actions ac = new Actions(GlobalVariable.myDriver);
		ac.moveToElement(el);
		Thread.sleep(2000);
		ac.click();
		ac.build().perform();
	}
	
	public static int DateExtract(String str) throws Exception
	{
		int value = Integer.parseInt(str.substring(8));
		return value;
		
	}
	
	

}
