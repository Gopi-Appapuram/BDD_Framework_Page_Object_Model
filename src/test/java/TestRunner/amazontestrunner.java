package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


/**
 * Runner class to run as Cucumber test runner file.
 */

// Configurations for Cucumber Test Runner
@CucumberOptions(
        tags = "", // Define the tags to be executed (if any)
        features = {
                "src/test/resources/Features"// Updated order of feature files
        },
        glue = {
                "StepDefinations" // Package where step definitions are located
        },
        monochrome = true, // Makes console out more readable
        plugin = {
                "pretty", // Prints the output in a readable format
                "html:test-output/CucumberReports/htmlreport.html" // Generates HTML report
        }
)

// Extends AbstractTestNGCucumberTests to run Cucumber tests with TestNG
public class amazontestrunner extends AbstractTestNGCucumberTests {

}
