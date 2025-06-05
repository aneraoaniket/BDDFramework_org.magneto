package PageObject;

import org.base.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationPage {
	WebDriver ldriver;
	public BaseClass baseClass = new BaseClass();

	public AccountCreationPage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	By fName = By.xpath("//input[@id='firstname']");

	By lName = By.xpath("//input[@id='lastname']");

	By email = By.xpath("//input[@id='email_address']");

	By password = By.xpath("//input[@id='password']");

	By createAnAccountBtn = By.xpath("//button[@type='submit']/span[normalize-space()='Create an Account']");

	By confirmPassword = By.xpath("//input[@id='password-confirmation']");

	By accountRegistrationMsg = By.xpath(
			"//div[@class='message-success success message']//div[contains(text(), 'Thank you for registering with Main Website Store.')]");

	By signoutArrowBtn = By.xpath("(//li[@class='customer-welcome']/span/button[@class='action switch'])[1]");

	By signOutBtn = By.xpath(
			"(//div[@class='customer-menu']/ul/li[@class='authorization-link']/a[normalize-space()='Sign Out'])[1]");

	By welcomeUserName = By.xpath("(//ul[@class='header links']/li/span[contains(text(),'Welcome')])[1]");

	By alreadyCreatedAccountMesg = By.xpath(
			"//div[@class='page messages']/div//div/div/div[text()='There is already an account with this email address. If you are sure that it is your email address, ']");

	By enterSameValueValidationMsg = By
			.xpath("//div[@class='field confirmation required']/div/div[text()='Please enter the same value again.']");

	public void enterFirstName(String userfName) {
		baseClass.typeInput(ldriver.findElement(fName), userfName);
	}

	public void enterLastName(String userlName) {
		baseClass.typeInput(ldriver.findElement(lName), userlName);
	}

	public void enterEmail(String userEmail) {
		baseClass.typeInput(ldriver.findElement(email), userEmail);
	}

	public void enterPassword(String userpwd) {
		baseClass.typeInput(ldriver.findElement(password), userpwd);
	}

	public void enterConfirmPassword(String userPwd) {
		baseClass.typeInput(ldriver.findElement(confirmPassword), userPwd);
	}

	public void clickOnCreateAccountBtn() {
		WebElement accoCreatebtn = ldriver.findElement(createAnAccountBtn);
		baseClass.scrollToElement(accoCreatebtn);
		baseClass.clickOnElement(accoCreatebtn);
		BaseClass.log.info("Clicked on element Create Account button");
	}

	public void verifyRegistrationSucessfully(String accountCreationMsg) {
		try {
			WebElement accCreationMsg = ldriver.findElement(accountRegistrationMsg);

			// Scroll to the element and wait for it to be displayed
			baseClass.scrollToElement(accCreationMsg);
			baseClass.waitForElementDisplayed(accCreationMsg);

			if (accCreationMsg.isDisplayed()) {
				BaseClass.log.info(accCreationMsg.getText());
				Assert.assertEquals(accountCreationMsg, accCreationMsg.getText());
			} else {
				Assert.assertFalse("Account creation message should NOT be displayed", accCreationMsg.isDisplayed());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnSignOutBtn() {
		WebElement signOutArrow = ldriver.findElement(signoutArrowBtn);
		baseClass.waitForElementDisplayed(signOutArrow);
		baseClass.scrollToElement(signOutArrow);
		baseClass.clickOnElement(signOutArrow);
		baseClass.clickOnElement(ldriver.findElement(signOutBtn));
		BaseClass.log.info("Clicked on sign out button");
	}

	public void verifyAbleToSeeWelcomePage(String expectedMsg) {
		try {
			WebElement welcomeTitle = ldriver.findElement(welcomeUserName);
			// Get the actual message text from the welcome title
			String actualMsg = welcomeTitle.getText();
			BaseClass.log.info("Login successful: " + actualMsg);

			// Assert that the actual message contains the expected message
			Assert.assertTrue("The welcome message does not contain the expected message",
					actualMsg.contains(expectedMsg));
			
			BaseClass.log.info("User logged in successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred while verifying welcome page: " + e.getMessage());
		}
	}

	public void validateAlreadyCreatedAccountMsg(String expectedMsg) {
		try {
			WebElement alreadyCreAccountMsg = ldriver.findElement(alreadyCreatedAccountMesg);

			Assert.assertTrue("Account creation message is not displayed.", alreadyCreAccountMsg.isDisplayed());

			String actualMsg = alreadyCreAccountMsg.getText();
			Assert.assertTrue(String.format("Expected message to contain '%s', but got '%s'.", expectedMsg, actualMsg),
					expectedMsg.contains(actualMsg));

			BaseClass.log.info("Validation passed with message: " + actualMsg);
		} catch (Exception e) {
			e.printStackTrace();
			BaseClass.log.error("Element not found for account creation message.", e);
			Assert.fail("Test failed due to missing element.");
		}
	}

	public void validateSameValueValidatationMsg(String expectedMsg) {
		try {
			WebElement validationMsgElement = ldriver.findElement(enterSameValueValidationMsg);

			// Assert that the element is displayed
			Assert.assertTrue("Validation message element is not displayed.", validationMsgElement.isDisplayed());

			// Get the actual message and assert it matches the expectation
			String actualMsg = validationMsgElement.getText();
			Assert.assertTrue(String.format("Expected message to contain '%s', but got '%s'.", expectedMsg, actualMsg),
					expectedMsg.contains(actualMsg));

			// Log success
			BaseClass.log.info("Validation passed with message: " + actualMsg);
		} catch (Exception e) {
			e.printStackTrace();
			BaseClass.log.error("Validation message element not found.", e);
			Assert.fail("Test failed due to missing validation message element.");
		}
	}
}
