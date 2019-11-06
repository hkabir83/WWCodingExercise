package question2.utilities;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.paulhammant.ngwebdriver.ByAngularRepeater;
import com.paulhammant.ngwebdriver.NgWebDriver;

import question2.pages.LandingScreen;



public abstract class WWDriver {
	public static WebDriver driver = null;
	private static NgWebDriver ngDriver = null;
	public static String landingPageUrl = "https://www.weightwatchers.com/us/";

	@BeforeSuite
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", new File("Jars").getAbsolutePath()+"/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}


	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public void navigateToLandingPage() {
		driver.navigate().to(landingPageUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}


	public static WebElement getElement(String locator,
			String attributeOfLocator) {

		if (locator.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("className")) {
			return driver.findElement(By.className(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("css")) {
			return driver.findElement(By.cssSelector(attributeOfLocator));
		} else if(locator.equalsIgnoreCase("AJclassName")) {
			return driver.findElement(ByAngularRepeater.className(attributeOfLocator));
		}else
			throw new NoSuchElementException("No Such Element"
					+ attributeOfLocator);
	}
	
	public static List<WebElement> getElements(String locator,
			String attributeOfLocator) {

		if (locator.equalsIgnoreCase("id")) {
			return driver.findElements(By.id(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("className")) {
			return driver.findElements(By.className(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("name")) {
			return driver.findElements(By.name(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("xpath")) {
			return driver.findElements(By.xpath(attributeOfLocator));
		} else if (locator.equalsIgnoreCase("css")) {
			return driver.findElements(By.cssSelector(attributeOfLocator));
		}else if(locator.equalsIgnoreCase("AJclassName")) {
			return driver.findElements(ByAngularRepeater.className(attributeOfLocator));
		} else
			throw new NoSuchElementException("No Such Element"
					+ attributeOfLocator);
	}
	
	public NgWebDriver getNgDriver() {
		ngDriver = new NgWebDriver((JavascriptExecutor) driver);
		return ngDriver;
	}
	
	public void waitForAngularRequestsToFinish() {
		getNgDriver().waitForAngularRequestsToFinish();
	}
		
	
	public LandingScreen landingScreen() {

		LandingScreen landingScreen = new LandingScreen(driver);
		return landingScreen;

	}

}
