package pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZerodhaForgotPasswordPage {
	
	@FindBy (xpath = "//a[text()='Forgot user ID or password?']") private WebElement forgot;
	@FindBy (xpath = "//label[text() = 'I remember my user ID']") private WebElement rememberUserId;
	@FindBy (xpath = "//label[text() = 'I forgot my user ID']") private WebElement forgotUserId;
	@FindBy (xpath = "//label[text()='E-mail']") private WebElement email;
	@FindBy (xpath = "//label[text()='SMS']") private WebElement sms;
	@FindBy (xpath = "(//a[@href=\'/\'])[2]") private WebElement backToLogIn;
	
	public ZerodhaForgotPasswordPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickOnForgotPassword ()
	{
		forgot.click();
	}
	public void selectRememberUserId (WebDriver driver,String userId,String panNumber,String emailId) throws InterruptedException
	{
		rememberUserId.click();
		
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(userId);
		Thread.sleep(2000);
		action.sendKeys(panNumber);
		Thread.sleep(2000);
		email.click();
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		email.sendKeys(emailId);
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(Keys.ENTER);
		
		action.build().perform();// build belongs to action class.
	}
	public void selectForgotUserId (WebDriver driver,String panNumber,String mobNumber) throws InterruptedException
	{
		forgotUserId.click();
		Thread.sleep(2000);
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(panNumber);
		Thread.sleep(2000);
		sms.click();
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(mobNumber);
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		action.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		action.build().perform();
	}
	public void clickOnBackToLogIn ()
	{
		backToLogIn.click();
	}

}
