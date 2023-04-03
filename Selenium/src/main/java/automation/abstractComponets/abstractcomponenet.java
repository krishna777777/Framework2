package automation.abstractComponets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.selenium.pageobject.OrderPage;
import automation.selenium.pageobject.cartPage;

public class abstractcomponenet {

	WebDriver driver;

	public abstractcomponenet(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// the data type is By for the Locator for the driver the data type is web
		// element
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// the data type is By for the Locator for the driver the data type is web
		// element
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public cartPage gotocart() {
		cartHeader.click();
		cartPage cartPage = new cartPage(driver);
		return cartPage;

	}

	public OrderPage gotoOrdersPage() {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;

	}

}
