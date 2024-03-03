package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*=myorders")
	WebElement orderHeader;
	
	public void goToCart()
	{
		cartButton.click();
	}
	
	public OrdersPage goToOrders()
	{		
		orderHeader.click();
		return new OrdersPage(driver);
	}
	
	public void waitForElementToAppear(By findby)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));	
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
