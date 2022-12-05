package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.ChromeBrowser;
import pom.ZerodhaHomePageSell;
import pom.ZerodhaLogInPage;
import utility.Excel;

public class ZerodhaHomePageSellTest  {
	WebDriver w;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void logInToZerodha () throws EncryptedDocumentException, IOException
	{
		w = ChromeBrowser.launchBrowser();
		w.manage().window().maximize();
		String user = Excel.fetchExcelSheet("Zerodha", 1, 0);
		String pass = Excel.fetchExcelSheet("Zerodha", 1, 1);
		String pin = Excel.fetchExcelSheet("Zerodha", 1, 2);
		ZerodhaLogInPage zerodhaLogInPage = new ZerodhaLogInPage(w);
		zerodhaLogInPage.enterUserId(user);
		zerodhaLogInPage.enterPassword(pass);
		zerodhaLogInPage.clickOnLogIn();
		zerodhaLogInPage.enterPin(w, pin);
		zerodhaLogInPage.clickOnContinue();
	}
	@Test
	public void searchAStock () throws InterruptedException
	{
		test = extent.createTest("searchAStock");
		ZerodhaHomePageSell zerodhaHomePageSell = new ZerodhaHomePageSell(w);
		zerodhaHomePageSell.searchStock("TATAELXSI");
		Thread.sleep(3000);
		String title = w.getTitle();
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(title, "Dashboard / Kite");
		zerodhaHomePageSell.clickOnSell(w,"TATAELXSI");
		soft.assertAll();
	}
	@Test
	public void chart() throws InterruptedException
	{
		test = extent.createTest("chart");
		ZerodhaHomePageSell zerodhaHomePageSell = new ZerodhaHomePageSell(w);
	
		zerodhaHomePageSell.searchStock("TATAPOWER");
	
		zerodhaHomePageSell.clickOnChart(w,"TATAPOWER");
	}
	@Test
	public void clickOnMarketDepth() throws InterruptedException
	{
		test = extent.createTest("clickOnMarketDepth");
		ZerodhaHomePageSell zerodhaHomePageSell = new ZerodhaHomePageSell(w);
		zerodhaHomePageSell.searchStock("TATAPOWER");
		Thread.sleep(4000);
		zerodhaHomePageSell.clickOnMarketDepth(w,"TATAPOWER");
	}
	@Test
	public void clickOnAdd() throws InterruptedException
	{
		test = extent.createTest("clickOnAdd");
		ZerodhaHomePageSell zerodhaHomePageSell = new ZerodhaHomePageSell(w);
		Thread.sleep(2000);
		zerodhaHomePageSell.searchStock("AARTIIND");
	
		zerodhaHomePageSell.clickOnAdd(w,"AARTIIND");
		
	}
	@AfterMethod
	public void postReport(ITestResult result)
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName());
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, result.getName());
		}
		else
		{
			test.log(Status.SKIP, result.getName());
		}
	}
	@AfterTest
	public void flushReport()
	{
		extent.flush();
	}

}
