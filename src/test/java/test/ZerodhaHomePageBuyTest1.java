package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pojo.FirefoxBrowser;
import pom.ZerodhaHomePageBuy;
import pom.ZerodhaHomePageBuy1;
import pom.ZerodhaLogInPage;
import utility.Excel;

public class ZerodhaHomePageBuyTest1 {
	WebDriver w;
	@BeforeMethod
	public void openFirefoxBrowser () throws EncryptedDocumentException, IOException
	{
		 w = FirefoxBrowser.firefoxBrowser();
		
		String user = Excel.fetchExcelSheet("Zerodha", 1, 0);
		String pass = Excel.fetchExcelSheet("Zerodha", 1, 1);
		String pin = Excel.fetchExcelSheet("Zerodha", 1, 2);
	    
		ZerodhaHomePageBuy1 zerodhaHomePageBuy1 = new ZerodhaHomePageBuy1(w);
		zerodhaHomePageBuy1.enterUserId(user);
		zerodhaHomePageBuy1.enterPassword(pass);
		zerodhaHomePageBuy1.clickOnLogIn();
		zerodhaHomePageBuy1.enterPin(w, pin);
		zerodhaHomePageBuy1.clickOnContinue();
		
	}
	@Test
	public void buyAStock() throws InterruptedException
	{
		ZerodhaHomePageBuy1 zerodhaHomePageBuy1 = new ZerodhaHomePageBuy1(w);
	    zerodhaHomePageBuy1.searchAStock("TATAPOWER");
	    zerodhaHomePageBuy1.clickOnBuy(w, "TATAPOWER");
	    zerodhaHomePageBuy1.buyAStock(w, "Dashboard / Kite", "5");
	    
	}
}
