package Automation.Selenium.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.selenium.pageobject.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// declaring the global properties to see in which browser to run the java
		// program .

		Properties properties = new Properties();
		// importing the stream class
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//automation//resources//GlobalData.properties");
		properties.load(fis);
		String browsername = properties.getProperty("browser");

		if (browsername.equals("edge")) {
			driver = new EdgeDriver();

		} else if (browsername.equals("firefox")) {

			driver = new FirefoxDriver();

		} else if (browsername.equals("chrome")) {
			driver = new ChromeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().window().maximize();

		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToo();

		return landingPage;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {

		// reading the json to String
		String jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// String to Hashmap
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

		// There is a External Dependency called jackson databind which is used to

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.close();

	}

}
