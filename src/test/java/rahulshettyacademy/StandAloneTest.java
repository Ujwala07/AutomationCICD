package rahulshettyacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productname="ADIDAS ORIGINAL";
		//WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("saavi07@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Saavi#07");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> products=driver.findElements(By.cssSelector(".card-body"));
		WebElement prod =products.stream().filter(s->s.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-child")).click();		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean isAdded=cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(isAdded);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();

		String msg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", msg);	
		driver.quit();
	}
	
	

}
