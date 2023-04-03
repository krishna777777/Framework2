package Automation.Selenium;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.Selenium.TestComponent.BaseTest;
import automation.selenium.pageobject.CheckoutPage;
import automation.selenium.pageobject.Confirmationpage;
import automation.selenium.pageobject.OrderPage;
import automation.selenium.pageobject.cartPage;
import automation.selenium.pageobject.productCatelogue;

public class submitOrder extends BaseTest {
	String productName = "ZARA COAT 3";
	String productName2 = "zara coat 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		productCatelogue productCatelogue = landingPage.LoginApplication(input.get("email"), input.get("password"));

		// productCatelogue productCatelogue = new productCatelogue(driver);
		List<WebElement> products = productCatelogue.getProductList();

		productCatelogue.addProductToCart(input.get("product"));
		cartPage cartPage = productCatelogue.gotocart();
		// cartPage cartPage = new cartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage CheckoutPage = cartPage.Checkout();
		CheckoutPage.selectCountry("india");
		Thread.sleep(5000);
		Confirmationpage Confirmationpage = CheckoutPage.submitOrder();
		String text = Confirmationpage.verifyConfirmationMessage();
		Assert.assertTrue(text.contentEquals("THANKYOU FOR THE ORDER."));

	}

	// we are checking the cart and seeing in the order history page the Zara coat
	// item is present
	// it depends on the execution of the submitOrder test so we are using the below
	// Annotation .
	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		productCatelogue productCatelogue = landingPage.LoginApplication("krishna99@gmail.com", "Krishna@77");
		OrderPage orderspage = productCatelogue.gotoOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName2));

	}

	public String getScreenshot(String testCaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";

	}

	// Extent Reports HTML Reports

	@DataProvider
	public Object[][] getData() throws IOException {
		// the object accepts any kind of data type instead of specifying string or int
		// the object is like a common data type

		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "krishna99@gmail.com"); map.put("password", "Krishna@77"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "gayathri@gmail.com"); map1.put("password",
		 * "Gayathri@123"); map1.put("product", "ADIDAS ORIGINAL");
		 */

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//Automation//data//Purchaseoder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
		// old format

		// return new Object[][] { { "krishna99@gmail.com","Krishna@77","ZARA COAT 3" },
		// { same here for gayathri }; };
	}
}
