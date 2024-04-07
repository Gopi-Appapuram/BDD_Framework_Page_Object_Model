package UtilityClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * @author LENOVO YOGA
 * 
 * Utility class to perform scrolling actions in a WebDriver test.
 */

public class ScrollUtility {
	WebDriver driver;
	JavascriptExecutor js;

	/**
	 * Constructor to initialize ScrollUtility with the WebDriver instance.
	 *
	 * @param driver The WebDriver instance to use for scrolling.
	 */
	public ScrollUtility(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Scrolls the web page to bring the specified element into view.
	 *
	 * @param element The WebElement to scroll into view.
	 */
	public void scrollElementIntoView(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(
				"arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
	}
}
