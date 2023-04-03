package automation.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponets.abstractcomponenet;

public class LandingPage extends abstractcomponenet {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// reference to the element
		PageFactory.initElements(driver, this);
	}

// the findby does it like this using the below syntax 	

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// using page factory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// .ng-tns-c4-2.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(id = "login")
	WebElement submit;

	// Action Method
	public productCatelogue LoginApplication(String email, String pass) {

		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		productCatelogue productCatelogue = new productCatelogue(driver);
		return productCatelogue;

	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();

	}

	// url

	public void goToo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
