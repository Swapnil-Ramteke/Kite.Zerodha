package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.ChromeBrowser;
import pom.ZerodhaLogInPage;
import utility.Excel;
import utility.Reports;

@Listeners(Listener.class)
public class KiteZerodhaLogInPageTest  {
	ExtentReports extent;
	ExtentTest test;

	
	@BeforeTest
	public void configureReport()
	{
		extent= Reports.generateReport("Kite.Zerodha", "Swapnil Ramteke");
	}
	
	@Test(priority = 1)
	public void logInWithValidCredentials() throws EncryptedDocumentException, IOException	{
		test = extent.createTest("logInWithValidCredentials");
		
		WebDriver w = ChromeBrowser.launchBrowser();
		
		ZerodhaLogInPage zerodhaLogInPage = new ZerodhaLogInPage(w);
		w.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
		String title= w.getTitle();
		System.out.println(title);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(title, "Kite - Zerodha's fast and elegant flagship trading platform");
		String user = Excel.fetchExcelSheet("Zerodha", 1, 0);
		zerodhaLogInPage.enterUserId(user);
		String pass = Excel.fetchExcelSheet("Zerodha", 1, 1);
		zerodhaLogInPage.enterPassword(pass);
		zerodhaLogInPage.clickOnLogIn();

		String pin = Excel.fetchExcelSheet("Zerodha", 1, 2);
		zerodhaLogInPage.enterPin(w,pin);
		zerodhaLogInPage.clickOnContinue();
		sa.assertAll();
	}
	@Test(priority = 2)
	public void logInWithoutPassword() throws EncryptedDocumentException, IOException {
		
		test = extent.createTest("logInWithoutPassword");
		
		WebDriver w = ChromeBrowser.launchBrowser();
		
		ZerodhaLogInPage zerodhaLogInPage = new ZerodhaLogInPage(w);
		w.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String pass = Excel.fetchExcelSheet("Zerodha", 2, 1);
		zerodhaLogInPage.enterPassword(pass);
		zerodhaLogInPage.clickOnLogIn();
	}
	@Test(priority =3)
	public void logInWithoutUserId() throws EncryptedDocumentException, IOException
	{
		test = extent.createTest("logInWithotUserId");
		WebDriver w = ChromeBrowser.launchBrowser();
		
		ZerodhaLogInPage zerodhaLogInPage = new ZerodhaLogInPage(w);
		w.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		String pass =Excel.fetchExcelSheet("Zerodha", 2, 1);
		zerodhaLogInPage.enterPassword(pass);
		zerodhaLogInPage.clickOnLogIn();
	}
	@Test(priority = 4)
	public void logInWithInvalidCredentials() throws EncryptedDocumentException, IOException
	{
		WebDriver w = ChromeBrowser.launchBrowser();
		
		ZerodhaLogInPage zerodhaLogInPage = new ZerodhaLogInPage(w);
        w.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String user = Excel.fetchExcelSheet("Zerodha", 2, 0);
		zerodhaLogInPage.enterUserId(user);
		String pass = Excel.fetchExcelSheet("Zerodha", 2, 1);
		zerodhaLogInPage.clickOnLogIn();
	}
	@AfterMethod
	public void postExecution(ITestResult result)
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName());
		}
		else if(result.getStatus() == ITestResult.FAILURE)
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
