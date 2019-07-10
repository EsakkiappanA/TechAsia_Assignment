package runnerClass;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features/SwagLabs.feature",glue= "stepDefinition",monochrome=true,plugin = {
"com.cucumber.listener.ExtentCucumberFormatter:output/report.html" })
public class RunnerClass {
	@AfterClass
	 public static void tearDown() {
	 Reporter.loadXMLConfig(new File("Resources/extent-config.xml"));
	 Reporter.setSystemInfo("user", System.getProperty("user.name"));
	 Reporter.setTestRunnerOutput("Validating the page");
	 
	 }	

}
