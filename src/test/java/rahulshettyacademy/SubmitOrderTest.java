package rahulshettyacademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	String productname="ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{		
		String country="ind";
		//WebDriverManager.chromedriver().setup();
		
		ProductCatalogue pc=lp.loginToApp(input.get("email"), input.get("pw"));
				
		pc.getProducts();
		WebElement prodname=pc.getProductName(input.get("product"));
		CartPage cp=pc.addProductToCart(prodname);
		pc.goToCart();	
				
		boolean isAdded=cp.checkForProductInCart(input.get("product"));				
		Assert.assertTrue(isAdded);
		CheckOutPage cop=cp.checkOutFromCart();
		
		cop.selectCountry(country);
		ConfirmationPage cfp= cop.placeOrder();
		
		String msg=cfp.getMessageText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", msg);	
		driver.quit();
	}	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue pc=lp.loginToApp("saavi07@gmail.com", "Saavi#07");
		OrdersPage op=pc.goToOrders();
		Assert.assertTrue(op.verifyOrderDisplay(productname));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data =getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"saavi07@gmail.com", "Saavi#07","ADIDAS ORIGINAL"},{"shridhara07@gmail.com", "Saavi#07","ZARA COAT 3"}};
	}*/
	
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<Object,Object> map=new HashMap<Object,Object>();
		map.put("email", "saavi07@gmail.com");
		map.put("pw", "Saavi#07");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<Object,Object> map1=new HashMap<Object,Object>();
		map1.put("email", "shridhara07@gmail.com");
		map1.put("pw", "Saavi#07");
		map1.put("product", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};
	}*/

}
