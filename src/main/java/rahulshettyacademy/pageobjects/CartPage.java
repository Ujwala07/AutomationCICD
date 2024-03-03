package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public boolean checkForProductInCart(String productname)
	{
		boolean isAdded=cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		return isAdded;
	}
	
	public CheckOutPage checkOutFromCart()
	{
		checkoutButton.click();
		return new CheckOutPage(driver);
	}
	
}
