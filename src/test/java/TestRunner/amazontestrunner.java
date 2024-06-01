package TestRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import static UtilityClasses.WebDriverManager.driver;

/**
 * Runner class to run as Cucumber test runner file.
 */

// Configurations for Cucumber Test Runner

@CucumberOptions(
        tags = "", // Define the tags to be executed (if any)
        features = {
                "src/test/resources/Features/1-User Login.feature",
                //"src/test/resources/Features/2-Searching for Products.feature",
                /*"src/test/resources/Features/3-Adding Items to Cart.feature",
                "src/test/resources/Features/4-Delete Items In Cart.feature",
                "src/test/resources/Features/5-Wishlist.feature"// Updated order of feature files*/
        },
        glue = {
                "StepDefinations", "Hooks" // Package where step definitions are located
        },
        plugin = {
                "pretty",
                "html:target/CucumberReports/htmlReport.html",
                "json:target/JsonReport/jsonReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun=false,
        monochrome = true, // Makes console output more readable
        publish = true

)

// Extends AbstractTestNGCucumberTests to run Cucumber tests with TestNG
public class amazontestrunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
