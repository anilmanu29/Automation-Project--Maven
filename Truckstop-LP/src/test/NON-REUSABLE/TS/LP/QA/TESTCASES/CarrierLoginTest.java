package TS.LP.QA.TESTCASES;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TS.LP.QA.BASE.TestBase;
import TS.LP.QA.PAGES.LoginPage;
import TS.LP.QA.PAGES.PayMeNow;
import TS.LP.QA.UTILITY.Xls_Reader;

public class CarrierLoginTest extends TestBase{
	LoginPage loginPage;
	PayMeNow paymenow;
	
	public CarrierLoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}

	@Test(priority=1)
	public void loginTest() throws InterruptedException{
		paymenow = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	
		Thread.sleep(2000);
	}
	
	/*@Test(dataProvider="getLoginData")
	public void CarrierLoginTest(String Username,String Password) throws IOException, InterruptedException
	{
		
		CarrierLoginTest lt = new CarrierLoginTest();
		lt.CarrierLoginTest(Username, Password);
		Thread.sleep(2000);
	}*/
	

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
