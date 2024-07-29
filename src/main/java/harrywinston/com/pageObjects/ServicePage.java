package harrywinston.com.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import harrywinston.com.abstractCompoent.AbstractComponent;

public class ServicePage extends AbstractComponent {

	public WebDriver driver;

	public ServicePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[href*='schedule-an-appointment'] span")
	private WebElement scheduleAppointment;

	@FindBy(id = "fxb_7a1564bf-c4ae-4f18-b750-49f856924890_Fields_4b8c9b17-a1e7-4886-98b3-c942b0aca806__Value")
	private WebElement salonCountry;

	@FindBy(id = "salonDropdown")
	private WebElement salonSelect;

	@FindBy(css = "div[class*=salon-detail__address]")
	private WebElement salonAddress;

	@FindBy(css = ".pickaday.datesalon")
	private WebElement dateInput;

	@FindBy(xpath = "//div[@class='pika-title']/div[1]")
	private WebElement monthPicker;

	@FindBy(css = ".pika-select.pika-select-month")
	private WebElement monthSelector;

	@FindBy(xpath = "//td[@data-day='2']")
	private WebElement datePicker;

	@FindBy(id = "fxb_7a1564bf-c4ae-4f18-b750-49f856924890_Fields_78a5767d-f5f3-490d-86fe-f1425e0cb15a__Value")
	private WebElement timeInput;

	@FindBy(xpath = "//fieldset/label[3]")
	private WebElement title;

	@FindBy(xpath = "//input[@data-title='First Name']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@data-title='Last Name']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@data-sc-field-name='emailaddress']")
	private WebElement emailAddress;

	@FindBy(xpath = "//input[@data-sc-field-name='telephone']")
	private WebElement phone;

	@FindBy(xpath = "//div[@class='sitecore-form__checkbox-list']/label[1]")
	private WebElement contacted;

	@FindBy(id = "fxb_7a1564bf-c4ae-4f18-b750-49f856924890_Fields_64ba333c-9bfb-4e07-937e-2ec779f502a4__Value")
	private WebElement yourPref;

	@FindBy(xpath = "//label[@for='fxb_7a1564bf-c4ae-4f18-b750-49f856924890_Fields_48b2e7fe-44fc-44a0-8b02-6f57360fc821__Value']")
	private WebElement terms;

	@FindBy(css = ".btn.btn--secondary")
	private WebElement submit;

	@FindBy(css = "div h1")
	private WebElement confMessage;

	public void goToScheduleAppointmentTab() {
		scheduleAppointment.click();
	}

	public void selectOptions(String country, String salon) throws InterruptedException {
		goToScheduleAppointmentTab();
		Select countryDropDown = new Select(salonCountry);
		countryDropDown.selectByVisibleText(country);

		Thread.sleep(5000);
		waitForWebElementToAppear(salonSelect);
		Select salonDropDown = new Select(salonSelect);
		salonDropDown.selectByVisibleText(salon);
	}

	public boolean verifyTheAddress(String expectedAddress) {
		String actualAddress = normalizeAddress(salonAddress.getText());
		expectedAddress = normalizeAddress(expectedAddress);

		return actualAddress.equalsIgnoreCase(expectedAddress);
	}

	private String normalizeAddress(String address) {
		// Remove leading and trailing spaces, normalize case, remove extra spaces
		return address.trim().replaceAll("\\s+", " ");
	}

	public void dateTimePicker(String month, String time) throws InterruptedException {
		// DatePicker
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(5000);
		waitForWebElementToAppear(dateInput);
		dateInput.click();
		waitForWebElementToAppear(monthPicker);
		monthPicker.click();
		Select monthDropDown = new Select(monthSelector);
		monthDropDown.selectByVisibleText(month);
		datePicker.click();

		// Time Picker
		Thread.sleep(5000);
		waitForWebElementToAppear(timeInput);
		Select timeDropDown = new Select(timeInput);
		timeDropDown.selectByVisibleText(time);
	}

	public void personalInformationSelection(String fname, String lname, String email, String phoneno,
			String yourPrefs) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		waitForWebElementToAppear(title);
		title.click();
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		emailAddress.sendKeys(email);
		phone.sendKeys(phoneno);
		contacted.click();
		Select yourPrefDropDown = new Select(yourPref);
		yourPrefDropDown.selectByVisibleText(yourPrefs);
		terms.click();
		submit.click();
	}

	public boolean verifyConfirmationMessage(String confirmationMessage) {
		waitForWebElementToAppear(confMessage);
		String message = confMessage.getText();
		boolean match = message.equalsIgnoreCase(confirmationMessage);

		return match;
	}
}