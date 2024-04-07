package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPage {
    private final WebDriver driver;

    // Locators
    private final By cancelOrderButton = By.id("cancelOrderButton");
    private final By orderStatus = By.id("orderStatus");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCancelOrder() {
        driver.findElement(cancelOrderButton).click();
    }

    public String getOrderStatus() {
        return driver.findElement(orderStatus).getText();
    }
}
