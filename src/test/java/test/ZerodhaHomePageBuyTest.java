package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.ChromeBrowser;
import pom.ZerodhaHomePageBuy;
import pom.ZerodhaLogInPage;
import utility.Excel;
import utility.Reports;

public class ZerodhaHomePageBuyTest extends BaseTest {
	
	ExtentReports extent;
	ExtentTest test;
	@BeforeTest
	public void configureReport()
	{
		extent = Reports.generateReport("ZerodhaBuyAStock", "Swapnil");
	}
	@BeforeMethod
	public void searchStockInSearchList () throws EncryptedDocumentException, IOException, InterruptedException
	{
		 w =ChromeBrowser.launchBrowser();
		 w.manage().window().maximize();
		
		String user = Excel.fetchExcelSheet("Zerodha", 1, 0);
		String pass = Excel.fetchExcelSheet("Zerodha", 1, 1);
		String pin = Excel.fetchExcelSheet("Zerodha", 1, 2);
		ZerodhaLogInPage zerodhaLogInPage = new ZerodhaLogInPage(w);
		zerodhaLogInPage.enterUserId(user);
		zerodhaLogInPage.enterPassword(pass);
		zerodhaLogInPage.clickOnLogIn();
		zerodhaLogInPage.enterPin(w,pin);
		zerodhaLogInPage.clickOnContinue();
	}
	@Test
	public void searchAStockFromSearchList () throws InterruptedException 
	{
		test = extent.createTest("searchAStockFromSearchList");
		ZerodhaHomePageBuy zerodhaHomePageBuy = new ZerodhaHomePageBuy(w);	
		zerodhaHomePageBuy.searchStock("TATASTLLP");
		int size = zerodhaHomePageBuy.getSearchListSize();
		Assert.assertTrue(size>0);
		zerodhaHomePageBuy.selectStockFromSearchList( w,"TATASTLLP");
		zerodhaHomePageBuy.buyAStock(w,"Dashboard / Kite", "6");
		
	}
	@Test(enabled = false)
	public void buyFromWatchlist () throws InterruptedException 
	{
		ZerodhaHomePageBuy zerodhaHomePageBuy = new ZerodhaHomePageBuy(w);
		zerodhaHomePageBuy.searchStock("TATAPOWER");
		zerodhaHomePageBuy.clickOnBuyFromWatchlist(w, "TATAPOWER");
		zerodhaHomePageBuy.buyAStock(w, "Dashboard / Kite","3");
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
	public void publishReport()
	{
		extent.flush();
	}
	
	

}
