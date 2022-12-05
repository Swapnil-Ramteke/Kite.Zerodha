package pom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ZerodhaLogInPage {
	
	@FindBy (xpath = "//input[@id='userid']") private WebElement userId;
	@FindBy (xpath = "//input[@id='password']") private WebElement password;
	@FindBy (xpath = "//button") private WebElement logIn;
	@FindBy (xpath = "//a[text()='Forgot user ID or password?']") private WebElement forgot;
	@FindBy (xpath = "//a[text()=\"Don't have an account? Signup now!\"]") private WebElement signUp;
	@FindBy (xpath = "//input[@id='pin']") private WebElement pin;
	@FindBy (xpath = "//button[@type='submit']") private WebElement continuee;
	@FindBy (xpath = "//input[@type='text']") private WebElement userNumber;
	@FindBy (xpath = "//button[@type='submit']") private WebElement numberContinue;
	
	
	public ZerodhaLogInPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void enterUserId(String user)
	{
		userId.sendKeys(user);
	}
	public void enterPassword(String pass)
	{
		password.sendKeys(pass);
	}
	public void clickOnLogIn()
	{
		logIn.click();
	}
	public void clickOnForgotPassword()
	{
		forgot.click();
	}
	public void clickOnSignUp()
	{
		signUp.click();
	}
	public void clickOnContinue ()
	{
		continuee.click();
	}
	public void enterPin (WebDriver driver,String pinNumber)
	{
		FluentWait<WebDriver>  wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofMillis(5000));
		wait.pollingEvery(Duration.ofMillis(100));
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.visibilityOf(pin));
		pin.sendKeys(pinNumber);
	}
	public void enterMobileNumber(String mobNumber)
	{
		userNumber.sendKeys(mobNumber);
	}
	public void clickOnContinueNumber()
	{
		numberContinue.click();
	}
	

}
