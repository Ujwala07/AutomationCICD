package rahulshettyacademy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportForSingleTest {

	ExtentReports extent;
	
	@BeforeTest
	public void extentReportConfiguration()
	{
		String filePath=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(filePath);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Single Test Case Reprt");
		
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "ujwala");
		
	}
	
	@Test
	public void launchBrowser()
	{
		ExtentTest test=extent.createTest("Launch Browser");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
		System.out.println(driver.getTitle());
		
		//test.addScreenCaptureFromBase64String(null);
		test.fail("result does not match");
		driver.close();
		extent.flush();
	}
}

