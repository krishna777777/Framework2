package Automation.Selenium;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.Selenium.TestComponent.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(groups = { "ErrorHandling" })
	public void SubmitOrder() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		landingPage.LoginApplication("krishna99@gmail.com", "Krishna");

		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
}
