package rahulshettyacademy.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsForFramework {
	
	 public static ExtentReports extent;
	 
	public static ExtentReports extentReportConfiguration()
	{
		String filePath=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(filePath);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Framework");
		
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "ujwala");
		return extent;
	}

}
