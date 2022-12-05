package pojo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser {
	
	public static WebDriver firefoxBrowser ()
	{
		System.setProperty("webdriver.gecko.driver", "D:\\Java + Eclipse Installation\\FireFox Driver\\geckodriver.exe");
	    
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://kite.zerodha.com/");
		return driver;
	}

}
