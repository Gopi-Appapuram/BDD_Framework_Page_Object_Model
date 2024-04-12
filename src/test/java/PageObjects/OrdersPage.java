package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdersPage {
    private final WebDriver driver;

    // Locators
    private final By yourOrdersLink = By.id("nav-orders");

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYourOrdersLink() {
        driver.findElement(yourOrdersLink).click();
    }

    public boolean arePreviousOrdersDisplayed() {
        return driver.findElement(By.id("ordersContainer")).isDisplayed();
    }

//    public void clickOrderDetails(String orderNumber) {
//        driver.findElement(By.xpath("//a[contains(text(), '" + orderNumber + "')]")).click();
//    }
}

