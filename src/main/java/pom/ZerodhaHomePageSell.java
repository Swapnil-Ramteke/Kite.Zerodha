package pom;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;

public class ZerodhaHomePageSell {
	
	@FindBy (xpath = "//input[@type='text']") private WebElement search;
	@FindBy (xpath = "//button[@data-balloon='Sell']") private WebElement sell;
	@FindBy (xpath = "//span[@class='tradingsymbol']") private List<WebElement> searchList;
	@FindBy (xpath = "(//span[@class=\'icon icon-trending-up\'])[1]") private WebElement chart;
	@FindBy (xpath = "//span[@class='icon icon-align-center']") private WebElement marketDepth;
	@FindBy (xpath = "//span[@class='icon icon-plus']") private WebElement add;
	@FindBy (xpath = "//button[@class='button-orange sell']") private WebElement watchListSell;
	
	public ZerodhaHomePageSell(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void searchStock (String nameOfStock)
	{
		search.sendKeys(nameOfStock);
	}
	public void clickOnSell(WebDriver driver,String nameOfSearchedStock) throws InterruptedException
	{
		for(int i = 0;i<searchList.size();i++)
		{
		WebElement currentStock =searchList.get(i);
		String nameOfStock = currentStock.getText();
		   if(nameOfStock.equals(nameOfSearchedStock))
		      {
	            	Actions action = new Actions(driver);
		            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		            action.moveToElement(currentStock);
		            action.perform();
		            sell.click();
		            break;
		            
	          }
		}
	}
	public void clickOnChart (WebDriver driver,String nameOfStock) throws InterruptedException
	{
		for(int i= 0;i<searchList.size();i++)
		{
			WebElement currentStock = searchList.get(i);
			String title = currentStock.getText();
			if(title.equals(nameOfStock))
			{
			
				Actions action = new Actions(driver); 
				action.moveToElement(currentStock);
				action.perform();
				Thread.sleep(2000);
			    chart.click();
			    break;
			}
		}
		
	}
	public void clickOnMarketDepth(WebDriver driver,String nameOfStock) throws InterruptedException
	{
		for(int i= 0;i<searchList.size();i++)
		{
			WebElement currentStock = searchList.get(i);
			String title = currentStock.getText();
			if(title.equals(nameOfStock))
			{
			
				Actions action = new Actions(driver); 
				action.moveToElement(currentStock);
				Thread.sleep(3000);
				action.perform();
				Thread.sleep(2000);
		        marketDepth.click();
		        break;
			}
		}
	}
	public void clickOnAdd(WebDriver driver,String nameOfStock) throws InterruptedException
	{
		for(int i= 0;i<searchList.size();i++)
		{
			WebElement currentStock = searchList.get(i);
			String title = currentStock.getText();
			if(title.equals(nameOfStock))
			{
			
				Actions action = new Actions(driver); 
				action.moveToElement(currentStock);
				action.perform();
				Thread.sleep(2000);
		        add.click();
		        break;
			}
		}
	}
}
