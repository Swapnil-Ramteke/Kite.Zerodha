package pom;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZerodhaHomePageBuy1 {
	
	@FindBy (xpath = "//input[@id='userid']") private WebElement userId;
	@FindBy (xpath = "//input[@id='password']") private WebElement password;
	@FindBy (xpath = "//button[@type='submit']") private WebElement logIn;
	@FindBy (xpath = "//input[@type='password']") private WebElement pin;
	@FindBy (xpath="//button[@type='submit']") private WebElement continuee;
	@FindBy (xpath = "//input[@type='text']") private WebElement search;
	@FindBy (xpath = "//span[@class='tradingsymbol']") private List<WebElement> stocks;
	@FindBy (xpath = "//button[@class='button-blue']") private WebElement buy;
	@FindBy (xpath = "//label[@class='su-radio-label']") private WebElement bse;
	@FindBy (xpath = "//label[text()='Regular']") private WebElement regular;
	@FindBy (xpath = "//label[@class='su-radio-label']") private WebElement cnc;
	@FindBy (xpath = "//input[@label=\'Qty.\']") private WebElement quantity;
	@FindBy (xpath = "//label[text()='Market']") private WebElement market;
	@FindBy (xpath = "//button[@type='submit']") private WebElement Buy;
	
public ZerodhaHomePageBuy1 (WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
public void enterUserId (String user)
{
	userId.sendKeys(user);
}
public void enterPassword (String pass)
{
	password.sendKeys(pass);
}
public void clickOnLogIn()
{
	logIn.click();
}
public void enterPin (WebDriver driver,String pinNumber)
{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(3000));
	wait.until(ExpectedConditions.visibilityOf(pin));
	pin.sendKeys(pinNumber);
}
public void clickOnContinue()
{
	continuee.click();
}
public void searchAStock (String stockName)
{
	search.sendKeys(stockName);
}
public void clickOnBuy (WebDriver driver,String nameOfSearchedStock)
{
	for(int a = 0;a<stocks.size();a++)
	{
		WebElement currentStock = stocks.get(a);
		String textOfCurrentStock = currentStock.getText();
		if(textOfCurrentStock.equals(nameOfSearchedStock))
		{
			Actions action = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
			action.moveToElement(currentStock);
			action.perform();
			buy.click();
			break;
		}
	}
}
public void buyAStock (WebDriver driver, String nameOfTitle, String numberOfStocks) throws InterruptedException
{
	
	Set<String> addresses = driver.getWindowHandles();
	Iterator<String> i = addresses.iterator();
	
	while(i.hasNext())
	{
		String address = i.next();
		driver.switchTo().window(address);
		String title = driver.getTitle();
		if(title.equals(nameOfTitle))
		{
			Actions action = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.visibilityOf(bse));
			action.moveToElement(bse);
			action.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			action.moveToElement(regular);
			action.click();
			Thread.sleep(2000);
			action.moveToElement(cnc);
			action.click();
			action.moveToElement(quantity);
			action.click();
			quantity.clear();
			action.sendKeys(numberOfStocks);
			action.moveToElement(market);
			action.click();
			action.moveToElement(Buy);
			action.click();
			action.build().perform();
			
		}
		
	}
}






}
