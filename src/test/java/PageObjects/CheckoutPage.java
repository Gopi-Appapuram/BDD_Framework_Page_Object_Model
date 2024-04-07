package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    // Locators
    private final By itemsList = By.cssSelector(".sc-list-item-content");
    private final By shippingDetailsForm = By.id("shippingAddress");
    private final By paymentDetailsForm = By.id("payment");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areItemsListed() {
        return driver.findElement(itemsList).isDisplayed();
    }

    public boolean isShippingDetailsFormDisplayed() {
        return driver.findElement(shippingDetailsForm).isDisplayed();
    }

    public boolean isPaymentDetailsFormDisplayed() {
        return driver.findElement(paymentDetailsForm).isDisplayed();
    }
}
