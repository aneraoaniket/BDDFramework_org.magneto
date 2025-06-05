package StepDefination;

import java.util.Map;

import org.base.BaseClass;
import org.base.ReadPropertFile;

import PageObject.AccountCreationPage;
import PageObject.CustomerLoginPage;
import PageObject.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AccountCreationStep extends BaseClass {

	public AccountCreationPage accountCreatPage;
	public CustomerLoginPage custLoginPage;
	public HomePage homepage;
	ReadPropertFile readProp = new ReadPropertFile();
	String testUrl = readProp.getUrl();

	@Given("User Launch Browser")
	public void User_Launch_Browser() {
		driver = launchBrowser();
		accountCreatPage = new AccountCreationPage(driver);
		homepage = new HomePage(driver);
		custLoginPage = new CustomerLoginPage(driver);
	}

	@When("User opens URL")
	public void user_opens_URL() {
		openUrl();
	}

	@When("user click on the Create Account button")
	public void user_click_on_the_Create_Account_button() {
		homepage.clickOnAccountCreation();
	}

	@When("user fill in the registration form with valid details:")
	public void user_fill_in_the_registration_form_with_valid_details(DataTable dataTable) {
		Map<String, String> userDetails = dataTable.asMap(String.class, String.class);
		System.out.println("#####" + userDetails.get("First Name"));
		accountCreatPage.enterFirstName(userDetails.get("First Name"));
		accountCreatPage.enterLastName(userDetails.get("Last Name"));
		accountCreatPage.enterEmail(userDetails.get("Email Address"));
		accountCreatPage.enterPassword(userDetails.get("Password"));
		accountCreatPage.enterConfirmPassword(userDetails.get("Confirm Password"));
	}

	@When("click on create an account button")
	public void click_on_create_an_account_button() {
		accountCreatPage.clickOnCreateAccountBtn();
	}

	@Then("user should see a confirmation message {string}")
	public void user_should_see_a_confirmation_message(String accountCreationMsg) {
		accountCreatPage.verifyRegistrationSucessfully(accountCreationMsg);
	}

	@Then("user should be able to sign out")
	public void user_should_be_able_to_sign_out() {
		accountCreatPage.clickOnSignOutBtn();
	}

	@And("close browser")
	public void close_Browser() {
		closeBrowser();
	}
	
	/////////////////////// Sign in with registerd Credentials////////////

	@Given("user navigate to the Sign In page")
	public void user_navigate_to_the_Sign_In_page() {
		homepage.clickOnSignInLink();
	}

	@When("user have an existing account with the following credentials:")
	public void user_have_an_existing_account_with_the_following_credentials(DataTable dataTable) {
		Map<String, String> userCredentials = dataTable.asMap(String.class, String.class);
		custLoginPage.enterRegisteredUserEmail(userCredentials.get("Email Address"));
		custLoginPage.enterUserPassword(userCredentials.get("Password"));
	}

	@When("user click on the Sign In button")
	public void user_click_on_the_Sign_In_button() {
		custLoginPage.clickOnSignInBtn();
	}

	@Then("user should see the {string} message")
	public void user_should_see_the_message(String string) {
		accountCreatPage.verifyAbleToSeeWelcomePage(string);
	}

	// Attempt to create account with existing email

	@And("user fill in the registration form with the following details")
	public void user_fill_in_the_registration_form_with_the_following_details(DataTable dataTable)
	{
		Map<String, String> userDetails = dataTable.asMap(String.class, String.class);
		System.out.println("#####" + userDetails.get("First Name"));
		accountCreatPage.enterFirstName(userDetails.get("First Name"));
		accountCreatPage.enterLastName(userDetails.get("Last Name"));
		accountCreatPage.enterEmail(userDetails.get("Email Address"));
		accountCreatPage.enterPassword(userDetails.get("Password"));
		accountCreatPage.enterConfirmPassword(userDetails.get("Confirm Password"));
	}
	
	@Then("user should see an error message {string}")
	public void user_should_see_an_error_message(String expectedMsg) {
		accountCreatPage.validateAlreadyCreatedAccountMsg(expectedMsg);
	}

	// Attempt to sign in with invalid credentials

	@When("user enter the following invalid credentials:")
	public void user_enter_the_following_invalid_credentials(DataTable dataTable) {
		Map<String, String> userCredentials = dataTable.asMap(String.class, String.class);
		custLoginPage.enterRegisteredUserEmail(userCredentials.get("Email Address"));
		custLoginPage.enterUserPassword(userCredentials.get("Password"));
	}

	@Then("user should see an error message for invalid credentials {string}")
	public void user_should_see_an_error_message_for_invalid_credentials(String expErrorMsg) {
		custLoginPage.verifyLoginErrorMsg(expErrorMsg);
	}
	
	// Fail to create an account with mismatched passwords
	@Then("user should see an error message for same value as {string}")
	public void user_should_see_an_error_message_for_same_value_as(String exxpectedMsg) {
		accountCreatPage.validateSameValueValidatationMsg(exxpectedMsg);
	}

}
