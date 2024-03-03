package rahulshettyacademy.stepDefinationFile;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinationImplementation extends BaseTest{
	
	public LandingPage lp;
	public ProductCatalogue pc;
	public CartPage cp;
	public ConfirmationPage cfp;
	
	@Given("I landed on Ecommerce Page.")
	public void i_landed_on_Ecommerce_Page() throws IOException
	{
		lp=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String un,String pw)
	{
		pc=lp.loginToApp(un, pw);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String product) throws InterruptedException
	{
		pc.getProducts();
		WebElement prodname=pc.getProductName(product);
		cp=pc.addProductToCart(prodname);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String product)
	{
		pc.goToCart();	
		
		boolean isAdded=cp.checkForProductInCart(product);				
		Assert.assertTrue(isAdded);
		CheckOutPage cop=cp.checkOutFromCart();
		
		cop.selectCountry("ind");
		cfp= cop.placeOrder();
	}
	
	@Then("{string} message is displayed on confirmation page.")
	public void message_is_displayed_on_confirmation_page(String string)
	{
		String msg=cfp.getMessageText();
		Assert.assertEquals(string, msg);	
		driver.quit();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void error_message_is_displayed(String str)
	{
		Assert.assertEquals(str, lp.getErrorMessage());
		driver.close();
	}
	
}
