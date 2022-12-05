package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pojo.ChromeBrowser;
import pom.ZerodhaForgotPasswordPage;
import pom.ZerodhaLogInPage;
import utility.Excel;

public class ZerodhaForgotPageTest {
	WebDriver w;
	@Test
	public void forgotPasswordRememberUserId() throws EncryptedDocumentException, IOException, InterruptedException
	{
		w = ChromeBrowser.launchBrowser();

		ZerodhaForgotPasswordPage zerodhaForgotPasswordPage = new ZerodhaForgotPasswordPage(w);
		
		String userId = Excel.fetchExcelSheet("Zerodha", 2, 0);
		String pan = Excel.fetchExcelSheet("Zerodha", 2, 4);
		String mobNumber = Excel.fetchExcelSheet("Zerodha", 2, 3);
		zerodhaForgotPasswordPage.clickOnForgotPassword();
		zerodhaForgotPasswordPage.selectRememberUserId(w, userId, pan, mobNumber);
		
	}
	
	@Test(enabled = false)
	public void forgotPasswordForgotUserId () throws EncryptedDocumentException, IOException, InterruptedException
	{
		w = ChromeBrowser.launchBrowser();
		
		ZerodhaForgotPasswordPage zerodhaForgotPasswordPage = new ZerodhaForgotPasswordPage(w);
		String pan = Excel.fetchExcelSheet("Zerodha", 2, 4);
		String mobNumber = Excel.fetchExcelSheet("Zerodha", 2, 3);
		zerodhaForgotPasswordPage.clickOnForgotPassword();
		zerodhaForgotPasswordPage.selectForgotUserId(w, pan, mobNumber);
		
		
	}

}
