package PageObjects;

import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    WebDriver driver;
    // Locators
    private final By emailInput = By.xpath("//input[@id ='ap_email']");
    private final By emailContinueButton = By.id("continue");
    private final By passwordInput = By.id("ap_password");
    private final By signInSubmitButton = By.id("signInSubmit");
    By welcomeMessage = By.id("nav-link-accountList-nav-line-1");
    private final By errorMessage = By.id("auth-error-message-box");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public void enterEmail(String email) throws InterruptedException {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(emailInput));
        driver.findElement(emailInput).sendKeys(email);
        Thread.sleep(5000);
    }
    public void clickContinue(){
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(emailContinueButton));
        driver.findElement(emailContinueButton).click();
    }

    public void enterPassword(String password) {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSignInSubmit() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(signInSubmitButton));
        driver.findElement(signInSubmitButton).click();
    }

    public String isWelcomeMessageDisplayed() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        String welcomeMessageText = "";
        try {
            if (driver.findElement(welcomeMessage).isDisplayed()) {
                highlight.highlightElement(driver.findElement(welcomeMessage));
                welcomeMessageText = driver.findElement(welcomeMessage).getText();
                System.out.println(welcomeMessageText);
            } else {
                System.out.println("Welcome message not found");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Welcome message element not found: " + e.getMessage());
        }
        return welcomeMessageText;
    }

    public boolean isErrorMessageDisplayed() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        if(driver.findElement(errorMessage).isDisplayed()){
            highlight.highlightElement(driver.findElement(errorMessage));
            String ErrorMessage = driver.findElement(errorMessage).getText();
            System.out.println(ErrorMessage);
            return true;
        } else{
            System.out.println("Message not found");
            return false;
        }
    }
}

