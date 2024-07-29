package harrywinston.com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import harrywinston.com.abstractCompoent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goTo() {
		driver.get("https://mc-fa595f2f-42bd-458f-bbb0-653808-cd.azurewebsites.net/en");
	}
}