package automation.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponets.abstractcomponenet;

public class productCatelogue extends abstractcomponenet{
	
	WebDriver driver ; 
	
	public productCatelogue(WebDriver driver ) {
		super(driver);
		this.driver= driver;
		
		// reference to the element 
		PageFactory.initElements(driver, this);
	}
	

	// using page factory 
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement>  products;
	
	

	@FindBy(css=".ng-animating")
	WebElement  spinner;
	
	By product1 = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-child");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(product1);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		
		return prod ; 

	}
	
	
	public void addProductToCart(String productName ) {
		
		WebElement prod = getProductByName(productName);
		
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
		
		
	}
	
	
	
	
}
