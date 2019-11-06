package question2.tests;

import org.testng.annotations.Test;
import question2.utilities.WWDriver;

public class TC_Verify_Flow_of_WW extends WWDriver{

	@Test
	public void verifyTest() {
		navigateToLandingPage();
		landingScreen().verifyPageTitle();
		landingScreen().clickOnFindAStudio();
		landingScreen().verifyPageTitleOfFindAStudio();
		landingScreen().searchZipCode();
		landingScreen().printTitleOfSearchAndDistance();
		landingScreen().clickOnLocationNameAndVerifyLocationName();
		landingScreen().todaysHoursOfOperation();
		landingScreen().printNumOfMeetingOfEachPerson();
	}
}
