package automation.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponets.abstractcomponenet;

public class cartPage extends abstractcomponenet{

	WebDriver driver;
	
	
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts; 
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	

	public cartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean VerifyProductDisplay(String productName) {
		
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		return match ;
	}
	
	
	public CheckoutPage Checkout() {
		checkoutele.click();
		
		CheckoutPage CheckoutPage = new CheckoutPage(driver);
		return CheckoutPage;
	}

}
