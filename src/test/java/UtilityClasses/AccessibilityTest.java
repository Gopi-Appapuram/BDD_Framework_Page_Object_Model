package UtilityClasses;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class AccessibilityTest {
    //private WebDriver driver;
    private static final URL scriptUrl = AccessibilityTest.class.getResource("/axe.min.js");

    SoftAssert softAssert = new SoftAssert();

    public void allyTest(WebDriver driver) throws Exception {
        allyAmazon(driver, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void allyAmazon(WebDriver driver, String resultPath) throws IOException {
        // User-specified folder path
        String userSpecifiedFolderPath = System.getProperty("user.dir") + "\\target\\AccessibilityReports\\";

        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.isEmpty()) {
            assertTrue("No violations found", true);
        } else {
            try {
                // Capture screenshots for each violation with detailed descriptions
                captureViolationScreenshots(driver, violations, userSpecifiedFolderPath+resultPath);
            } catch (Exception e) {
                System.out.println(e);
            }

            // Create directories if they don't exist
            String reportFolderPath = userSpecifiedFolderPath + "Reports\\";
            createFolderIfNotExists(reportFolderPath);

            // Write results to JSON file
            AXE.writeResults(reportFolderPath + resultPath.replace("_", ""), violations);

            softAssert.assertFalse(false, AXE.report(violations));

        }

        softAssert.assertAll();
    }

    private void captureViolationScreenshots(WebDriver driver, JSONArray violations, String folderPath) throws IOException {
        for (int i = 0; i < violations.length(); i++) {
            JSONObject violation = violations.getJSONObject(i);
            String violationDescription = violation.getString("description");
            JSONArray nodes = violation.getJSONArray("nodes");

            System.out.println("Violation: " + violationDescription);

            for (int j = 0; j < nodes.length(); j++) {
                JSONObject node = nodes.getJSONObject(j);
                JSONArray targets = node.getJSONArray("target");

                for (int k = 0; k < targets.length(); k++) {
                    String cssSelector = targets.getString(k);
                    WebElement element = driver.findElement(By.cssSelector(cssSelector));

                    // Scroll the element into view
                    scrollToElement(driver, element);

                    // Highlight the element
                    highlightElement(driver, element);

                    // Take a screenshot and get the file path
                    String screenShotFolderPath =  folderPath + "ScreenShots\\";
                    String fileName = "violation-" + i + "-" + j + "-" + k + ".png";
                    createFolderIfNotExists(screenShotFolderPath);
                    String screenshotPath = takeScreenshot(driver, screenShotFolderPath + fileName);

                    // Unhighlight the element
                    unhighlightElement(driver, element);

                    // Update the JSON with the screenshot path
                    updateViolationWithScreenshotPath(node, screenshotPath);

                    // Print violation details
                    System.out.println("  Node " + j + ": Target: " + cssSelector);
                    System.out.println("  Screenshot saved as: " + fileName);
                }
            }
        }
    }

    private void scrollToElement(WebDriver driver, WebElement element) {
        // Use JavaScript to scroll the element into view and center it in the viewport
        String script = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    private void highlightElement(WebDriver driver, WebElement element) {
        // Use JavaScript to highlight the element
        String script = "arguments[0].style.border='3px solid red'";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    private void unhighlightElement(WebDriver driver, WebElement element) {
        // Use JavaScript to unhighlight the element
        String script = "arguments[0].style.border=''";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    private String takeScreenshot(WebDriver driver, String fileName) {
        // Take a screenshot and save it to the specified file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File(fileName));
            return new File(fileName).getAbsolutePath();  // Return the path of the screenshot
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateViolationWithScreenshotPath(JSONObject node, String screenshotPath) {
        // Add screenshot path to the node JSON
        if (screenshotPath != null) {
            node.put("screenshot", screenshotPath);
        }
    }

    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Create the directory if it doesn't exist
        }
    }
}
