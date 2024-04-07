package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProfilePage {
    private final WebDriver driver;

    // Locators
    private final By nameField = By.id("name");
    private final By emailField = By.id("email");
    private final By addressField = By.id("address");
    private final By saveChangesButton = By.id("saveChangesButton");
    private final By successMessage = By.id("successMessage");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getName() {
        return driver.findElement(nameField).getAttribute("value");
    }

    public String getEmail() {
        return driver.findElement(emailField).getAttribute("value");
    }

    public String getAddress() {
        return driver.findElement(addressField).getAttribute("value");
    }

    public void updateName(String newName) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(newName);
    }

    public void clickSaveChanges() {
        driver.findElement(saveChangesButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }
}

