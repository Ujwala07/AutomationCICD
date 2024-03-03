package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver launchAndInitializeBrowser() throws IOException
	{
		
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		p.load(fis);
		
		String browser=System.getProperty("browser")!= null ? System.getProperty("browser") : p.getProperty("browser");		
		
		if(browser.contains("Chrome"))
		{	
			ChromeOptions options=new ChromeOptions();
			
			//to check if we need to run test case in headless mode
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //full screen
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destPath=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		File dest=new File(destPath);
		FileUtils.copyFile(source, dest);
		return destPath;
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		//Read JSON data to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//Convert json content string to hashmap using jackson databind dependency and object mapper class
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException 
	{
		driver=launchAndInitializeBrowser();
		lp=new LandingPage(driver);
		lp.goToLoginPage();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.quit();
	}

}
