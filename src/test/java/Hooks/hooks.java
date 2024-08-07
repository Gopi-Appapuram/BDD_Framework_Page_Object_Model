package Hooks;



import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.zeroturnaround.zip.ZipUtil;


import java.io.File;

import static UtilityClasses.WebDriverManager.driver;
import static org.testng.AssertJUnit.assertTrue;

public class hooks {

    private static ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();

    @AfterStep
    public static void takeScreenShot(Scenario scenario) {
        if (!scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Passed Scenario: " + scenario.getName().replace("\\s", ""));
        } else {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Scenario: " + scenario.getName().replace("\\s", ""));
        }
    }



    @AfterAll
    public static void zipFolder() {
        String sourcePath = "target/Reports";
        String destinationPath = "reports";
        ZipUtil.pack(new File(sourcePath), new File(destinationPath + ".zip"));
    }

    /*@AfterAll
    public static void testMyWebPage() {
        AxeBuilder axeBuilder = new AxeBuilder();

        try {
            Results axeResults = axeBuilder.analyze(driver);
            assertTrue(axeResults.violationFree());
        } catch (RuntimeException e) {
            // Do something with the error
        }
    }*/

    @Before
    public void beforeScenario(Scenario scenario) {
        currentScenario.set(scenario);
    }

    public static String getUniqueIdentifier() {
        Scenario scenario = currentScenario.get();
        if (scenario != null) {
            String scenarioName = scenario.getName().replace(" ", "");
            int methodName = scenario.getLine();; // Adjust index as needed
            return scenarioName.concat("_").concat(String.valueOf(methodName));
        }
        return "Unknown";
    }

    @AfterAll
    public static void closeBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
