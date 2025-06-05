
package Hooks;

import org.base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {

	@After(order = 0)
	public void takeScreenShotOfThePage(Scenario scenario) {
		String scrennshotName = scenario.getName().toUpperCase().replaceAll(" ", "_");
		// downcast the driver to access TakesScreenshot method
		TakesScreenshot srcShot = (TakesScreenshot) BaseClass.driver;
		// capture screenshot as output type FILE
		byte[] srclocation = srcShot.getScreenshotAs(OutputType.BYTES);
		scenario.attach(srclocation, "image/png", scrennshotName);
	}
}
