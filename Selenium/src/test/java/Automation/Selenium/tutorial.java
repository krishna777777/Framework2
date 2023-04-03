package Automation.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.selenium.pageobject.LandingPage;

public class tutorial {
	public static void main(String args[]) throws InterruptedException {

		String pname = "ZARA COAT 3";

		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("krishna99@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Krishna@77");
		driver.findElement(By.id("login")).click();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(pname)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-child")).click();

		// explicit wait to wait till the toast message appears

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		// ng-animating
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating "))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// gettting the text from the cart sections
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		/*
		 * for (int i =0;i<names.size();i++) { String name =
		 * driver.findElements(By.xpath("//div[@class='cartSection']//h3")).get(i).
		 * getText();
		 * 
		 * if (name.equals(pname)) { System.out.println(" the name is present "+pname);
		 * } }
		 */

		Boolean match = cartProducts.stream().anyMatch(nad -> nad.getText().equalsIgnoreCase(pname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		// Actions a = new Actions(driver);
		// a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")),"india").build().perform();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		// driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement Element = driver.findElement(By.linkText("PLACE ORDER"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Thread.sleep(5000);
		Element.click();

	}

}
