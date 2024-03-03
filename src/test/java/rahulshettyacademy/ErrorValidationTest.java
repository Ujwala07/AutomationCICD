package rahulshettyacademy;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void submitOrderError() throws InterruptedException, IOException
	{
		lp.loginToApp("saavi0723@gmail.com", "Saavi#07");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException
	{
		String productname="ADIDAS ORIGINAL";		
		ProductCatalogue pc=lp.loginToApp("shridhara07@gmail.com", "Saavi#07");
				
		pc.getProducts();
		WebElement prodname=pc.getProductName(productname);
		CartPage cp=pc.addProductToCart(prodname);
		pc.goToCart();	
				
		boolean isAdded=cp.checkForProductInCart("adidas");				
		Assert.assertFalse(isAdded);
	}
	

}
