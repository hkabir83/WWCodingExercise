package question2.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import question2.utilities.BasePage;

public class LandingScreen extends BasePage{
	
	private String title = "WW (Weight Watchers): Weight Loss & Wellness Help";
	private String findWWStudioTitle = "Find WW Studios & Meetings Near You | WW USA";
	private String findAStudio_id = "ela-menu-visitor-desktop-supplementa_find-a-studio";
	private String searchBox_id = "meetingSearch";
	private String button_xpath = "//*[@id=\"content\"]/div/div/ui-view/ui-view/div/div[2]/form/div[1]/button";
	private String aj_locationName_class = "location__name";
	private String aj_locationDistance_class = "location__distance";
	private String aj_timeOfOperation = "hours-list-item-hours";
	private String aj_person_meeting = "schedule-detailed-day-meetings-item-leader";
	private String aj_schedule_detailed_day = "schedule-detailed-day";
	
	public LandingScreen(WebDriver driver) {
	}
	
	public void verifyPageTitle() {
	    String pageTitle = getPageTitle();
	    if(pageTitle.contentEquals(title)) {
	    	System.out.println(pageTitle+ " : Page Title matched !!");
	    }else {
	    	System.out.println(pageTitle+ " : Page Title did not match!!");
	    }
	}
	
	public void clickOnFindAStudio() {
		clickButton(getId(), findAStudio_id, "Click on Find A Studio", getYes(), "Unable to click on Find A Studio");
	}
	
	public void verifyPageTitleOfFindAStudio() {
		
		waitUntilElementVisible(findWWStudioTitle, 40);
		String titleAfterClickFindAStudio = getPageTitle();

		if(titleAfterClickFindAStudio.contains(findWWStudioTitle)) {
			System.out.println(titleAfterClickFindAStudio+ " : Title matched After Click Find A Studio !!");
		}else {
			System.out.println(titleAfterClickFindAStudio+ " : Title did not match After Click Find A Studio !!");
		}
	}
	
	public void searchZipCode() {
		enterDataIntoTextField(getId(), searchBox_id, "10011",
				"Entered Search Text as 10011", getYes(),
				"Unable to enter text into search field");
		clickButton(getXpath(), button_xpath, "Click on Search button", getYes(), "Unable to click on Search button");
		
	}
	
	public void printTitleOfSearchAndDistance() {
		waitForAngularRequestsToFinish();
		String locationName = getPageText(getAJclassName(), aj_locationName_class, "Got Location name", getYes(), "Unable to get Location name");
		String locationDistance = getPageText(getAJclassName(), aj_locationDistance_class, "Got Location Distance", getYes(), "Unable to get Location Distance");
		System.out.println("Location Name : "+locationName);
		System.out.println("Location Distance : "+locationDistance);
		
	}
	
	public void clickOnLocationNameAndVerifyLocationName() {
		String locationName = getPageText(getAJclassName(), aj_locationName_class, "Got Location name", getYes(), "Unable to get Location name");
		clickButton(getAJclassName(), aj_locationName_class, "Click on Location Name", getYes(), "Unable to click on Location Name");
		waitForAngularRequestsToFinish();
		String locNameAfterClickOnlocationName = getPageText(getAJclassName(), aj_locationName_class, "Got Location name", getYes(), "Unable to get Location name");
		if(locNameAfterClickOnlocationName.contentEquals(locationName)) {
			System.out.println("Location Name matched !!");
		}else {
			System.out.println("Location Name did not match !!");
		}
	}
	
	public void todaysHoursOfOperation() {
		String todays_hours_of_operation = getTodaysHoursOfOperation(getAJclassName(), aj_timeOfOperation);
		System.out.println("Todays hours of operation :");
		System.out.println(todays_hours_of_operation);
	}
	
	public void printNumOfMeetingOfEachPerson() {
		List<WebElement> elements = getElements(getAJclassName(), aj_schedule_detailed_day);
		List<WebElement> person = getWebElements(elements, aj_person_meeting);
		System.out.println("Today's Meeting Count : ");
		getNumberOfMeeting(person);
	}

}
