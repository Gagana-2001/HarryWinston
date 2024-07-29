package harrywinston.com.baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import harrywinston.com.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializer() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\harrywinston\\com\\resources\\GlobalData.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// Fire-Fox
		} else if (browser.equalsIgnoreCase("edge")) {
			// Edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		return driver;
	}

	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializer();
		landingPage = new LandingPage(driver);
		landingPage.goTo();

		return landingPage;
	}

	@AfterMethod
	public void closeConnection() {
		driver.close();
	}

	public String getScreenShot(String testCase, WebDriver driver) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCase + ".png");
		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir") + "//reports//" + testCase + ".png";
	}
}