package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) 	
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(xpath="//div[contains(@class,'ng-trigger-flyInOut')]")
	WebElement error;
	
	public void goToLoginPage()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(error);
		return error.getText();
	}
	
	public ProductCatalogue loginToApp(String useremail, String pw)
	{
		email.sendKeys(useremail);
		password.sendKeys(pw);
		login.click();
		return new ProductCatalogue(driver);
	}
	

}
