package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage {
    private final WebDriver driver;

    // Locators
    private final By wishlistItems = By.cssSelector(".a-row.wl-item");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemDisplayedInWishlist(String itemName) {
        return driver.findElement(By.xpath("//span[contains(text(), '" + itemName + "')]")).isDisplayed();
    }

    public void removeItemFromWishlist(String itemName) {
        driver.findElement(By.xpath("//span[contains(text(), '" + itemName + "')]/ancestor::div[@class='a-row wl-item']/descendant::input")).click();
    }
}
