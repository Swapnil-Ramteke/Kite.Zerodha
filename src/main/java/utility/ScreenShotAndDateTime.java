package utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import pojo.ChromeBrowser;

public class ScreenShotAndDateTime {
	
	public static String dateAndTime()
	{
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy_MMM_dd HH_mm_ss");
		String currentDateTime = format.format(date);
		return currentDateTime;
	}
	public static void getScreenshot(WebDriver w,String name) throws IOException
	{
		String currentDateTime = ScreenShotAndDateTime.dateAndTime();
		 w = ChromeBrowser.launchBrowser();
	    
		TakesScreenshot tss =(TakesScreenshot)w;
		File source = tss.getScreenshotAs(OutputType.FILE);
		
		File destination = new File("D:\\Automation Practice\\ScreenShots\\ZerodhaMaven\\"+name+" "+currentDateTime+".png");
		
		FileHandler.copy(source,destination);
	}

}
