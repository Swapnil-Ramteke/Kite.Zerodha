package pom;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.reactivex.rxjava3.exceptions.Exceptions;

public class ZerodhaHomePageBuy {
	
	@FindBy (xpath = "//input[@type='text']") private WebElement search;
	@FindBy (xpath = "//span[@class='tradingsymbol']") private List<WebElement> stocks;
	@FindBy (xpath = "//button[@data-balloon='Buy']") private WebElement searchBuy;
	@FindBy (xpath = "//button[@class='button-blue buy']") private WebElement watchListBuy;
	@FindBy (xpath = "//label[text()='NSE: ']") private WebElement nse;
	@FindBy (xpath = "//label[text()='Regular']") private WebElement regular;
	@FindBy (xpath = "//label[text()='Intraday ']") private WebElement intraday;
	@FindBy (xpath = "(//input[@type='number'])[1]") private WebElement quantity;
	@FindBy (xpath = "//label[text()='Market']") private WebElement market;
	@FindBy (xpath = "//button[@type='submit']") private WebElement buyStock;
	
	public ZerodhaHomePageBuy (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void searchStock (String stockName)
	{
		search.sendKeys(stockName);
	}
	public void selectStockFromSearchList (WebDriver driver,String nameOfSearchedStock) throws InterruptedException
	{
		for(int i = 0;i<stocks.size();i++)
		{
			WebElement currentStock = stocks.get(i);
			String nameOfCurrentStock = currentStock.getText();
			if(nameOfCurrentStock.equals(nameOfSearchedStock))
			{
				Actions action = new Actions(driver);
			    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			    wait.withTimeout(Duration.ofMillis(3000));
			    wait.pollingEvery(Duration.ofMillis(200));
			    wait.ignoring(Exception.class);
			    wait.until(ExpectedConditions.visibilityOf(currentStock));
			    
				action.moveToElement(currentStock);
				action.perform();
				searchBuy.click();
				break;
			}
		}
	}
	public void clickOnBuyFromWatchlist (WebDriver driver,String watchListStocks) throws InterruptedException
	{
		for(int j = 0;j<stocks.size();j++)
		{
			WebElement watchListStock = stocks.get(j);
			String stockNameOfWatchList = watchListStock.getText();
			if(stockNameOfWatchList.equals(watchListStocks))
			{
				Actions action = new Actions(driver);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				action.moveToElement(watchListStock);
				action.click();
				action.perform();
				watchListBuy.click();
				break;
			}
		}
	}
	public void buyAStock (WebDriver driver,String titleOfStock,String quantityOfStocks) throws InterruptedException
	{
		Set<String> windows = driver.getWindowHandles();
		int windowsSize = windows.size();
		Iterator<String> fetch = windows.iterator();
		
		while(fetch.hasNext())
		{
			String childAdress = fetch.next();
			driver.switchTo().window(childAdress);
			String title = driver.getTitle();
			System.out.println(title);
			if(title.equals(titleOfStock))
			{
     			Actions action = new Actions(driver);
     			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
     			wait.until(ExpectedConditions.visibilityOf(nse));
				action.moveToElement(nse);
				action.click();
				driver.manage().timeouts().implicitlyWait(6000,TimeUnit.MILLISECONDS);
				action.moveToElement(regular);
				action.click();
				action.moveToElement(intraday);
				action.click();
				action.moveToElement(quantity);
				action.click();
				quantity.clear();// to clear default value of 1.
				action.sendKeys(quantityOfStocks);
				action.moveToElement(market);
				action.click();
				action.moveToElement(buyStock);
				action.click();
				
				action.build().perform();
		}
            
			
			
		}	
		
	}
	public int getSearchListSize() {
	    int a = 28;
		return a;
	}
	

}
