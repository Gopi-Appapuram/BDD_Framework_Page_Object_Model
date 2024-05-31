package Hooks;



import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import static UtilityClasses.WebDriverManager.driver;

public class hooks {


    @AfterStep
    public void takeScreenShot(Scenario scenario) {
        if(!scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName().replace(" ", "") + "_passed.png");
        } else if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName().replace(" ", "") + "_failed.png");
        }
    }

    @AfterAll
    public static void closeBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
