package com.lp.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.lp.qa.util.ReadExcel;
import com.lp.qa.util.TestUtil;
import com.lp.qa.util.WebEventListener;
import com.lp.qa.util.Xls_Reader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;



import java.util.ArrayList;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	//public String filepath=System.getProperty("user.dir")+ "/DataFolder/LoadPayTestData.xlsx";
	public static WebEventListener eventListener;
	
	static Xls_Reader reader;
	
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/REUSABLE/com/lp"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+ "/src/main/NON-REUSABLE/Drivers/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/src/main/NON-REUSABLE/Drivers/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "/src/main/NON-REUSABLE/Drivers/IEDriverServer.exe");	
			driver = new InternetExplorerDriver(); 
		}
		
		
	
		
			
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new com.lp.qa.util.WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		//driver.get(prop.getProperty("filepath"));
	}
	
		/*public static ArrayList<Object[]> getDataFromExcel(){

			ArrayList<Object[]> myData = new ArrayList<Object[]>();
			
			try {
				reader = new Xls_Reader(System.getProperty("user.dir")+ "/src/main/java/com/lp"
						+ "/qa/testdata/LoadPayTestData.xlsx");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (int rowNum = 2; rowNum <= reader.getRowCount("LoadPay"); rowNum++) {
				
					String Username = reader.getCellData("LoadPay", "Username", rowNum);
					String Password = reader.getCellData("LoadPay", "Password", rowNum);
					Object ob[] = {Username, Password };
					myData.add(ob);
						
		
			}
		return myData;
		
	}*/
	/*
	@DataProvider(name="getLoginData")
	public Object[][] getCellData() throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		
	
	ReadExcel read=new ReadExcel();
	
	return read.getCellData(prop.getProperty("filepath", "LoadPay"));
}*/
	
	

}
