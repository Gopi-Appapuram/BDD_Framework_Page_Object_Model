package UtilityClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Gopi Appapuram
 * 
 * Utility class to handle switching between windows or tabs in WebDriver tests.
 */

public class WindowHandles {
	private WebDriver driver;

	/**
	 * Constructor to initialize WindowHangels with the WebDriver instance.
	 *
	 * @param driver The WebDriver instance to use for window handling.
	 */
	public WindowHandles(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Switches to the tab with the specified index.
	 *
	 * @param index The index of the tab to switch to.
	 */
	public void switchToTab(int index) {
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}

	/**
	 * Closes the current tab and switches to the tab with the specified index.
	 *
	 * @param index The index of the tab to switch to after closing the current tab.
	 */
	public void closeAndSwitchToTab(int index) {
		driver.close();
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}

	/**
	 * Closes all open tabs except the main window.
	 *
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	public void closeAllTabs() throws InterruptedException {
		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();
		// Iterate through each handle and close the window
		for (String handle : allWindowHandles) {
			driver.switchTo().window(handle);
			if (!driver.getTitle().equals("Myntra")) {
				driver.close();
				Thread.sleep(2500);
			}
		}
	}

	/**
	 * Switches to a window with the specified title.
	 *
	 * @param windowTitle The title of the window to switch to.
	 */
	public void switchToWindow(String windowTitle) {
		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();
		// iterate through open windows. If the title of the window matches the
		// specified title,
		// switch to it.
//		for (String windowHandle : allWindowHandles) {
//			
//			driver.switchTo().window(windowHandle);
//			if (driver.getTitle().equalsIgnoreCase(windowTitle)) {
//				break;
//			}
//		}
		
		for (String windowHandle : allWindowHandles) {
			driver.switchTo().window(windowHandle);
			String title = driver.getTitle();
			if (title.equalsIgnoreCase(windowTitle)) {
				break;
			}
		}
		
	}

	// The following methods are commented out but can be used if needed:
//
//	 /**
//	 * Switches to the next tab.
//	 */
//	 public void switchToNextTab() {
//	 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//	 driver.switchTo().window(tabs.get(1));
//	 }
//	
//	 /**
//	 * Closes the current tab and switches to the next tab.
//	 */
//	 public void closeAndSwitchToNextTab() {
//	 driver.close();
//	 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//	 driver.switchTo().window(tabs.get(1));
//	 }
//	
//	 /**
//	 * Switches to the previous tab.
//	 */
//	 public void switchToPreviousTab() {
//	 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//	 driver.switchTo().window(tabs.get(0));
//	 }
//	
//	 /**
//	 * Closes the current tab and switches to the main window.
//	 */
//	 public void closeTabAndReturn() {
//	 driver.close();
//	 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//	 driver.switchTo().window(tabs.get(0));
//	 }
//	
//	 /**
//	 * Switches to the previous tab and closes it.
//	 */
//	 public void switchToPreviousTabAndClose() {
//	 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//	 driver.switchTo().window(tabs.get(1));
//	 driver.close();
//	 }
}
