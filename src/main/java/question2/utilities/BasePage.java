package question2.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.paulhammant.ngwebdriver.ByAngularRepeater;

public class BasePage extends WWDriver{
	private final static String xpath = "xpath";
	private final static String id = "id";
	private final static String cssSelector = "css";
	private final static String name = "name";
	private final static String className = "className";
	private final static String tagName = "tagName";
	private final static String yes = "Yes";
	private final static String no = "No";
	private final static String AJclassName = "AJclassName";
	

	public static String getXpath() {
		return xpath;
	}

	public static String getId() {
		return id;
	}

	public static String getCssselector() {
		return cssSelector;
	}

	public static String getName() {
		return name;
	}

	public static String getClassName() {
		return className;
	}

	public static String getTagName() {
		return tagName;
	}

	public static String getYes() {
		return yes;
	}

	public static String getNo() {
		return no;
	}
	
	public static String getAJclassName() {
		return AJclassName;
	}

	private void getShowStopperStatus(String blocker, String errorMessage) {

		String showStopper = blocker;

		if (showStopper.equalsIgnoreCase("yes")) {
			navigateToLandingPage();
			throw new NoSuchElementException(errorMessage);
		} else if (showStopper.equalsIgnoreCase("no")) {
			Reporter.log(errorMessage);
		}

	}


	public List<WebElement> getWebElements(String locator,
			String attributeOfLocator, String blocker,
			String errorMessage) {
		List<WebElement> element = null;
		try {
			element = getElements(locator, attributeOfLocator);
		} catch (NoSuchElementException | TimeoutException e) {
			getShowStopperStatus(blocker, errorMessage);
			e.printStackTrace();
		}

		return element;
	}
	
	public List<WebElement> getWebElements(List<WebElement> ele, String attributeOfLocator){
		
		List<WebElement> element = null;
		element = ele.get(getDayOfTheWeek() - 1).findElements(ByAngularRepeater.className(attributeOfLocator));
		
		return element;
	}
	
	
	public static void getNumberOfMeeting(List<WebElement> eleList) {
		Map<String, Integer> meetingFrequency = new HashMap<String, Integer>(); 
		List<String> list = new ArrayList<String>();
		for(int x=0; x<eleList.size(); x++) {
			list.add(eleList.get(x).getText());
		}
        for (String i : list) { 
            Integer j = meetingFrequency.get(i);
            meetingFrequency.put(i, (j == null) ? 1 : j + 1); 
        } 

        for (Map.Entry<String, Integer> val : meetingFrequency.entrySet()) { 
            System.out.println(val.getKey()+" "+ val.getValue()); 
        } 
	}


	public void clickButton(String locator, String attributeOfLocator,
			String passMessage, String blocker, String errorMessage) {
		try {
			WebElement ele = getElement(locator, attributeOfLocator);
			ele.click();
			Reporter.log(passMessage);
		} catch (NoSuchElementException | TimeoutException e) {
			getShowStopperStatus(blocker, errorMessage);
		}
	}
	

	public void enterDataIntoTextField(String locator,
			String attributeOfLocator, String value, String passMessage,
			String blocker, String errorMessage) {

		try {
			WebElement ele = getElement(locator, attributeOfLocator);
			ele.sendKeys(value);
			Reporter.log(passMessage);
		} catch (NoSuchElementException | TimeoutException e) {
			getShowStopperStatus(blocker, errorMessage);
		}

	}


	public String getPageText(String locator, String attributeOfLocator,
			String info, String blocker, String errorMessage) {
		String text = null;
		try {
			WebElement ele = getElement(locator, attributeOfLocator);
			text = ele.getText();
			Reporter.log(info);
		} catch (NoSuchElementException | TimeoutException e) {
			getShowStopperStatus(blocker, errorMessage);
			e.printStackTrace();
		}

		return text;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public Calendar getTodaysDate() {
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar;
	}
	
	public int getDayOfTheWeek() {
		return getTodaysDate().get(Calendar.DAY_OF_WEEK);
	}

	public String getTodaysHoursOfOperation(String locator, String attributeOfLocator) {
		return getElements(locator, attributeOfLocator).get(getDayOfTheWeek() - 1).getText();
	}
	

	public void waitUntilElementVisible(String title, int timeout) {
		WebDriverWait expectedWaits = new WebDriverWait(driver, timeout);
		expectedWaits.until(ExpectedConditions.titleContains(title));
	}

}
