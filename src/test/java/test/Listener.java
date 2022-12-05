package test;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utility.ScreenShotAndDateTime;

public class Listener extends BaseTest implements ITestListener 
{
	public void onTestStart (ITestResult result)
	{
		System.out.println(result.getName() + " has started ");
	}
	public void onTestSuccess (ITestResult result)
	{
		System.out.println(result.getName() +" passed");
	}
	public void onTestFailure (ITestResult result)
	{
		try 
		{
			ScreenShotAndDateTime.getScreenshot(w, result.getName());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println(result.getName() + " failed");
	}
	public void onTestSkipped (ITestResult result)
	{
		System.out.println(result.getName() + " has been skipped");
	}
	
	

}
