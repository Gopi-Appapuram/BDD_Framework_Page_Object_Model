package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "", // Define the tags to be executed (if any)
        features = {
                "src/test/resources/Features/1-User Login.feature",
                "src/test/resources/Features/2-Searching for Products.feature",
                "src/test/resources/Features/3-Adding Items to Cart.feature",
                "src/test/resources/Features/4-Delete Items In Cart.feature",
                "src/test/resources/Features/5-Wishlist.feature"// Updated order of feature files
        },
        glue = {
                "StepDefinations" // Package where step definitions are located
        },
        monochrome = true, // Makes console output more readable
        publish = true,
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                /*"pretty",
                "html:test-output/CucumberReports/htmlReport.html", // Generates HTML report
                "json:test-output/CucumberReports/JsonReport.json"*/
        }
)
public class JunitRunner {
}
