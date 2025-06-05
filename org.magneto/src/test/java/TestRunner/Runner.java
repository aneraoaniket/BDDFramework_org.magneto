package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features/CreateAccountAndSignIn.feature",
		// features = ".//<Feature file folderName>/featurefilename.feature", <---To run all feature files
		// features ={".//<Feature file folderName>/featurefil1.feature", .//<Feature file folderName>/featurefil2.feature} <------To run specific feature files
		glue = { "StepDefination", "Hooks" }, plugin = { "pretty", "html:target/cucumber-reports/reports.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, dryRun = false,

//Remove unnecessary character from console 
		monochrome = true)

public class Runner {

}
