package harrywinston.com.test;

import org.testng.Assert;

import harrywinston.com.baseTest.BaseTest;
import harrywinston.com.pageObjects.ServicePage;

public class Test extends BaseTest {

	String country = "Germany";
	String salon = "Harry Winston Düsseldorf";
	String address = "ADDRESS: Königsallee 10 40212 Düsseldorf, Germany";

	String month = "July";
	String time = "2:00 PM";

	String fname = "gagana";
	String lname = "CM";
	String email = "gagana.cm@valtech.com";
	String phone = "9535925188";
	String yourPrefs = "Jewelry";
	String confirmationMessage = "Thank you for submitting  appointment request.";

	@org.testng.annotations.Test
	public void serviceScheduleAppointment() throws InterruptedException {

		ServicePage servicePage = landingPage.goToServiceTab();
		servicePage.selectOptions(country, salon);
		Boolean match = servicePage.verifyTheAddress(address);
		Assert.assertTrue(match);
		servicePage.dateTimePicker(month, time);
		servicePage.personalInformationSelection(fname, lname, email, phone, yourPrefs);
		Boolean matchConfMessage = servicePage.verifyConfirmationMessage(confirmationMessage);
		Assert.assertTrue(matchConfMessage);
	}

}
