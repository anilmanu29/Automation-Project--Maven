package TS.RA.QA.TESTCASES;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TS.RA.QA.BASE.TestBase;
import TS.RA.QA.PAGES.LoginPage;
import TS.RA.QA.PAGES.rates;

public class LoginTest extends TestBase{
	LoginPage loginPage;
	rates Rates;
	
	public LoginTest()
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
		Rates = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	
		Thread.sleep(5000);
	}
	

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
