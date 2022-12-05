package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	public static ExtentReports generateReport(String testName,String testerName)
	{
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("ZerodhaReport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("TestName", testName);
		extent.setSystemInfo("Tester", testerName);
	    return extent;
	}
	
	

}
