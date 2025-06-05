package org.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Select sel;
	public static Actions action;
	public static JavascriptExecutor js;
	public static Logger log;
	ReadPropertFile readProp = new ReadPropertFile();
	String bName = readProp.getBrowser();
	String testUrl = readProp.getUrl();

	public WebDriver launchBrowser() {
		try {
			log = LogManager.getLogger("Magneto");
			if (bName.equals("chrome")) {
				// WebDriverManager.chromedriver().setup();
				String projectPath = System.getProperty("user.dir");
				System.setProperty("webdriver.chrome.driver", projectPath + "./Drivers/chromedriver.exe");

				ChromeOptions opt = new ChromeOptions(); //
				opt.addArguments("--remote-allow-origins=*"); //
				opt.addArguments("--start-maximized");
				opt.addArguments("--incognito");
				opt.addArguments("--disable-notifications");

				driver = new ChromeDriver(opt);
			} else if (bName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (bName.equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			log.info("Launched " + bName + " Browser");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			action = new Actions(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public void openUrl() {
		try {
			// Navigate to the given URL
			log.info("Opening Url");
			driver.get(testUrl);
			log.info("Opened Url : " + testUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPageTitle() {
		try {
			// Get the title of the page
			String pageTitle = driver.getTitle();
			return pageTitle;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clickOnElementJS(WebElement element) {
		try {
			if (element.isDisplayed()) {
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);
				log.info("Element clicked successfully : " + element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnElement(WebElement element) {
		try {
			if (element.isDisplayed()) {
				element.click();
				log.info("Element clicked successfully : " + element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			log.info("Scroll successfully : " + element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeBrowser() {
		if (driver != null) {
			try {
				driver.close();
				log.info("Browser closed successfully.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void typeInput(WebElement element, String input) {
		try {
			waitForElementDisplayed(element);
			element.clear();
			if (input instanceof String) {
				element.sendKeys(input);
				log.info("Entered : " + input);
			} else {
				element.sendKeys(String.valueOf(input));
				log.info("Entered : " + input);
			}
			log.info(input + " Successfully entered in field");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @After() public void takeScreenShotOfThePage(Scenario scenario) { String
	 * scrennshotName=scenario.getName().toUpperCase().replaceAll(" ", "_"); //
	 * downcast the driver to access TakesScreenshot method TakesScreenshot srcShot
	 * = (TakesScreenshot) driver; // capture screenshot as output type FILE byte[]
	 * srclocation = srcShot.getScreenshotAs(OutputType.BYTES);
	 * scenario.attach(srclocation, "image/png", scrennshotName); }
	 */
	public boolean waitForElementDisplayed(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Waiting till visibility of element to be displayed " + element);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String generateEmailId() {
		return (RandomStringUtils.randomAlphabetic(4));
	}

	//code added to check conflict
	 public static void main(String[] args) {
	        System.out.println("Start");
	        System.out.println("Line A");
	        System.out.println("Line B");
	        System.out.println("Line C");
	        System.out.println("Line D");
	        System.out.println("Line E");
	        System.out.println("End");
	    }
}
