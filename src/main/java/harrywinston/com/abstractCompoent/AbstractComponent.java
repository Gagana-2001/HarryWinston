package harrywinston.com.abstractCompoent;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import harrywinston.com.pageObjects.ServicePage;

public class AbstractComponent {

	public WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(css = "a[href='/en/services']")
	private WebElement service;

	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(element));
	}

	public ServicePage goToServiceTab() {
		service.click();

		return new ServicePage(driver);
	}
}