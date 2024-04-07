package UtilityClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

/**
 * @author Gopi Appapuram
 *
 * This class manages the WebDriver instance for the tests.
 * It provides methods to get and quit the WebDriver for various browsers.
 */
public class WebDriverManager {

    public static WebDriver driver;

    /**
     * Private constructor to prevent instantiation
     */
    public WebDriverManager() {
        // Prevent instantiation
    }

    /**
     * Method to get the WebDriver instance for Chrome browser.
     * If the WebDriver is not already initialized, it initializes a new ChromeDriver instance,
     * sets implicit wait time, deletes cookies, and maximizes the window.
     *
     * @return The WebDriver instance for Chrome browser
     */
    public static WebDriver getChromeDriver() {
        if (driver == null) {
            // Initialize Chrome WebDriver
            driver = new ChromeDriver();
            //driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Method to get the WebDriver instance for Firefox browser.
     * If the WebDriver is not already initialized, it initializes a new FirefoxDriver instance,
     * sets implicit wait time, deletes cookies, and maximizes the window.
     *
     * @return The WebDriver instance for Firefox browser
     */
    public static WebDriver getFirefoxDriver() {
        if (driver == null) {
            // Initialize Firefox WebDriver
            driver = new FirefoxDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Method to get the WebDriver instance for Internet Explorer browser.
     * If the WebDriver is not already initialized, it initializes a new InternetExplorerDriver instance,
     * sets implicit wait time, deletes cookies, and maximizes the window.
     *
     * @return The WebDriver instance for Internet Explorer browser
     */
    public static WebDriver getIEDriver() {
        if (driver == null) {
            // Initialize Internet Explorer WebDriver
            driver = new InternetExplorerDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Method to quit the WebDriver instance.
     * If the WebDriver is not null, it quits the WebDriver and sets it to null.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

