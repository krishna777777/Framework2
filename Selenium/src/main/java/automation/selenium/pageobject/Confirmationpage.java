package automation.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponets.abstractcomponenet;

public class Confirmationpage extends abstractcomponenet {
	
	WebDriver driver ;

	public Confirmationpage(WebDriver driver) {
		super(driver);
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
		
	}
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage ; 
	
	
	public String verifyConfirmationMessage() {
		return confirmationMessage.getText();
	}
	


}
