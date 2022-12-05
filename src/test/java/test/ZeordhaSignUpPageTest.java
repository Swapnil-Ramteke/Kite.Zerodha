package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
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
import pom.ZerodhaLogInPage;
import pom.ZerodhaSignUpPage;
import utility.Excel;
import utility.Reports;

public class ZeordhaSignUpPageTest extends BaseTest{
	WebDriver w;
	ExtentReports data;
	ExtentTest test; // for adding status of test
	@BeforeTest
	public void configureReport()
	{
		data = Reports.generateReport("ZerodhaSignUpPage", "Swapnil");
	}
	@BeforeMethod
	public void openZerodha()
	{
		w = ChromeBrowser.launchBrowser();
	}
	@Test 
	public void clickOnSignUp () throws InterruptedException, EncryptedDocumentException, IOException
	{
		String number = Excel.fetchExcelSheet("Zerodha", 1, 3);
		System.out.println(number);
		ZerodhaSignUpPage zerodhaSignUpPage = new ZerodhaSignUpPage(w);
		zerodhaSignUpPage.clickOnSignUp(w, number);
		Thread.sleep(3000);
		zerodhaSignUpPage.enterMobileNumber(number);
		Thread.sleep(3000);
		zerodhaSignUpPage.clickOnContinueNumber();
	}
	@AfterMethod
	public void postExecution(ITestResult result)
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
	
	

}
