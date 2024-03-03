package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By prod=By.cssSelector(".card-body");
	By msg=By.id("toast-container");
	
	public List<WebElement> getProducts()
	{
		waitForElementToAppear(prod);
		return products;
	}
	
	public WebElement getProductName(String productname)
	{		
		WebElement prod =getProducts().stream().filter(s->s.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		return prod;		
	}
	
	public CartPage addProductToCart(WebElement prodname) throws InterruptedException
	{
		prodname.findElement(By.cssSelector(".card-body button:last-child")).click();
		waitForElementToAppear(msg);
		waitForElementToDisappear(spinner);
		return new CartPage(driver);
	}
	
	
}
