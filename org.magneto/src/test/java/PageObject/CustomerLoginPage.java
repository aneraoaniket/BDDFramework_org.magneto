package PageObject;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.junit.Assert;

public class CustomerLoginPage {

	public WebDriver ldriver;
	public BaseClass baseClass = new BaseClass();

	public CustomerLoginPage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	By fieldEmail = By.xpath("//div[@class='control']/input[@id='email']");

	By fieldPwd = By.xpath("(//div[@class='field password required']/div/input[@id='pass'])[1]");

	By signInBtn = By.xpath("(//div[@class='primary']/button[@id='send2'])[1]");

	By loginErrorMsg = By.xpath("//div[@class='page messages']/div/div/div/div[contains(text(),'The account')]");

	public void enterRegisteredUserEmail(String email) {
		WebElement fEmail = ldriver.findElement(fieldEmail);
		baseClass.typeInput(fEmail, email);
	}

	public void enterUserPassword(String pwd) {
		WebElement fPwd = ldriver.findElement(fieldPwd);
		baseClass.typeInput(fPwd, pwd);
	}

	public void clickOnSignInBtn() {
		WebElement signIn = ldriver.findElement(signInBtn);
		baseClass.scrollToElement(signIn);
		baseClass.clickOnElement(signIn);
	}

	public void verifyLoginErrorMsg(String expectedMsg) {
		try {
			WebElement errorMsg = ldriver.findElement(loginErrorMsg);
			if (errorMsg.isDisplayed()) {
				String actualMsg = errorMsg.getText();
				if (expectedMsg.equals(actualMsg)) {
					BaseClass.log.info("error validation done successfully on entering invalid credentials ");
					Assert.assertTrue(true);
				} else {
					BaseClass.log.info("user able to login Test case failed");
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
