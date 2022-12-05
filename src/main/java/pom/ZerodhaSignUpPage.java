package pom;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZerodhaSignUpPage {
	@FindBy (xpath = "//a[text()=\"Don't have an account? Signup now!\"]") private WebElement signUpOnLogIn;
	@FindBy (xpath = "//input[@type='text']") private WebElement userNumber;
	@FindBy (xpath = "//button[@type='submit']") private WebElement numberContinue;
	@FindBy (xpath = "(//a[text() ='Signup'])[1]") private WebElement signUp;//This is child browser pop_up element
	
	public ZerodhaSignUpPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSignUp(WebDriver driver,String mobNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		signUpOnLogIn.click();
		
		Set<String> addresses = driver.getWindowHandles();
		Iterator<String> i = addresses.iterator();
		
		while(i.hasNext())
		{
			String nextElement = i.next(); 
			driver.switchTo().window(nextElement);
			String title = "signup-mobile-input su-input-group su-static-label";
			String currentTitle = driver.getTitle();
			if(title.equals(currentTitle))
			{
				Thread.sleep(3000);
				userNumber.sendKeys(mobNumber);
			}
		}
	}
	public void enterMobileNumber(String mobNumber)
	{
		userNumber.sendKeys(mobNumber);
	}
	public void clickOnContinueNumber()
	{
		numberContinue.click();
	}
	public void clickOnSignUpOnLogInPage()
	{
		signUpOnLogIn.click();
	}

}
