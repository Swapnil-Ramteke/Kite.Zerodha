package utility;

import java.io.File;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import pojo.ChromeBrowser;

public class ScreenShot {
	
	public static void takeScreenshot(String name) throws IOException
	{
		WebDriver driver = ChromeBrowser.launchBrowser();
	    
	    TakesScreenshot tss =(TakesScreenshot)driver;
	    File source = tss.getScreenshotAs(OutputType.FILE);
	    File destination = new File("D:\\Automation Practice\\ScreenShots\\ZerodhaMaven\\"+name+" "+".png");
	    FileHandler.copy(source, destination);
	}

}
