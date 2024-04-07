package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private final WebDriver driver;

    // Locators
    private final By profileLink = By.id("profileLink");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProfileLink() {
        driver.findElement(profileLink).click();
    }
}

