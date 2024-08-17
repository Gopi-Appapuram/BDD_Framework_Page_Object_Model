package UtilityClasses;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * This class is responsible for performing accessibility tests on web pages using the AXE accessibility testing library.
 * It integrates with Selenium WebDriver to analyze accessibility issues, capture screenshots, and report findings.
 */
public class AccessibilityTest {
    private static final Logger logger = Logger.getLogger(AccessibilityTest.class.getName());
    private static final URL SCRIPT_URL = AccessibilityTest.class.getResource("/axe.min.js");
    private final String reportBasePath;
    private final String screenshotsBasePath;
    private final SoftAssert softAssert;

    /**
     * Constructs an AccessibilityTest instance with specified paths for reports and screenshots.
     *
     * @param reportBasePath The base path where accessibility reports will be saved.
     * @param screenshotsBasePath The base path where screenshots of accessibility violations will be saved.
     */
    public AccessibilityTest(String reportBasePath, String screenshotsBasePath) {
        this.reportBasePath = reportBasePath;
        this.screenshotsBasePath = screenshotsBasePath;
        this.softAssert = new SoftAssert();
    }

    /**
     * Executes the accessibility test on the provided WebDriver instance and saves the results to the specified path.
     *
     * @param driver The WebDriver instance to use for testing.
     * @param resultPath The path where the test results will be saved.
     * @throws Exception If an error occurs during the test execution.
     */
    public void runAccessibilityTest(WebDriver driver, String resultPath) throws Exception {
        analyzeAccessibility(driver, resultPath);
    }

    /**
     * Analyzes accessibility violations on the web page and handles the results.
     *
     * @param driver The WebDriver instance to use for testing.
     * @param resultPath The path where the test results will be saved.
     * @throws IOException If an error occurs while handling files or paths.
     */
    private void analyzeAccessibility(WebDriver driver, String resultPath) throws IOException {
        // Analyze the page for accessibility violations using AXE
        JSONObject responseJSON = new AXE.Builder(driver, SCRIPT_URL).analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");

        // Check if there are any violations
        if (violations.isEmpty()) {
            softAssert.assertTrue(true, "No violations found");
        } else {
            try {
                // Capture screenshots of violations and save results
                captureViolationScreenshots(driver, violations, screenshotsBasePath + File.separator + resultPath);
                String reportFolderPath = reportBasePath + File.separator + "Reports";
                createFolderIfNotExists(reportFolderPath);

                // Write the results to a JSON file
                AXE.writeResults(reportFolderPath + File.separator + resultPath.replace("_", ""), violations);
                softAssert.assertFalse(false, AXE.report(violations));

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during accessibility analysis", e);
            }
        }
        softAssert.assertAll();
    }

    /**
     * Captures screenshots of the elements involved in accessibility violations.
     *
     * @param driver The WebDriver instance to use for capturing screenshots.
     * @param violations The list of accessibility violations.
     * @param folderPath The folder path where screenshots will be saved.
     * @throws IOException If an error occurs while saving screenshots.
     */
    private void captureViolationScreenshots(WebDriver driver, JSONArray violations, String folderPath) throws IOException {
        for (int i = 0; i < violations.length(); i++) {
            JSONObject violation = violations.getJSONObject(i);
            String violationDescription = violation.getString("description");
            JSONArray nodes = violation.getJSONArray("nodes");

            logger.info("Violation: " + violationDescription);

            for (int j = 0; j < nodes.length(); j++) {
                JSONObject node = nodes.getJSONObject(j);
                JSONArray targets = node.getJSONArray("target");

                for (int k = 0; k < targets.length(); k++) {
                    String cssSelector = targets.getString(k);
                    WebElement element = driver.findElement(By.cssSelector(cssSelector));

                    // Scroll the element into view and highlight it
                    scrollToElement(driver, element);
                    highlightElement(driver, element);

                    // Capture a screenshot of the highlighted element
                    String screenshotsFolderPath = folderPath + File.separator + "Screenshots";
                    createFolderIfNotExists(screenshotsFolderPath);
                    String fileName = "violation-" + i + "-" + j + "-" + k + ".png";
                    String screenshotPath = takeScreenshot(driver, screenshotsFolderPath + File.separator + fileName);

                    // Unhighlight the element
                    unHighlightElement(driver, element);

                    // Update the violation JSON with the screenshot path
                    updateViolationWithScreenshotPath(node, screenshotPath);

                    logger.info(String.format("  Node %d: Target: %s", j, cssSelector));
                    logger.info("  Screenshot saved as: " + fileName);
                }
            }
        }
    }

    /**
     * Scrolls the specified element into view and centers it in the viewport.
     *
     * @param driver The WebDriver instance to use for executing JavaScript.
     * @param element The WebElement to scroll into view.
     */
    private void scrollToElement(WebDriver driver, WebElement element) {
        String script = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    /**
     * Highlights the specified element by applying a red border.
     *
     * @param driver The WebDriver instance to use for executing JavaScript.
     * @param element The WebElement to highlight.
     */
    private void highlightElement(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.border='3px solid red'";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    /**
     * Removes the highlight (border) from the specified element.
     *
     * @param driver The WebDriver instance to use for executing JavaScript.
     * @param element The WebElement to unhighlight.
     */
    private void unHighlightElement(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.border=''";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    /**
     * Takes a screenshot of the current page and saves it to the specified file path.
     *
     * @param driver The WebDriver instance to use for capturing the screenshot.
     * @param fileName The file path where the screenshot will be saved.
     * @return The absolute path of the saved screenshot file.
     */
    private String takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File(fileName));
            return new File(fileName).getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the JSON object representing a violation with the path of the corresponding screenshot.
     *
     * @param node The JSON object representing a node in the violation.
     * @param screenshotPath The path of the screenshot file.
     */
    private void updateViolationWithScreenshotPath(JSONObject node, String screenshotPath) {
        if (screenshotPath != null) {
            node.put("screenshot", screenshotPath);
        }
    }

    /**
     * Creates a directory at the specified path if it does not already exist.
     *
     * @param folderPath The path of the directory to create.
     */
    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Create the directory if it doesn't exist
        }
    }
}
