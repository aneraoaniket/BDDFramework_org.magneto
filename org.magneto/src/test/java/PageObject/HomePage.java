package PageObject;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	// Create new Customer Account Personal Info
	WebDriver ldriver;
	public BaseClass baseClass=new BaseClass();
	
	public HomePage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	By signInLink = By.xpath("(//ul/li/a[normalize-space()='Sign In'])[1]");
	By linkCreateAccount = By.xpath("(//ul/li/a[normalize-space()='Create an Account'])[1]");

	public void clickOnAccountCreation() {
		WebElement createAccount = ldriver.findElement(linkCreateAccount);
		baseClass.clickOnElement(createAccount);
	}

	public void clickOnSignInLink() {
		WebElement btnSignIn = ldriver.findElement(signInLink);
		baseClass.clickOnElement(btnSignIn);
	}

}
