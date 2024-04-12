package PageObjects;

import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.text.Highlighter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {
    WebDriver driver;

    // Locators
    private final By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");
    private final By searchButton = By.id("nav-search-submit-button");


    public HomePage(WebDriver driver) {

        this.driver = driver;

    }

    public void isHomePageDisplayed(){
        String homePageTitle = driver.getTitle();
        assertEquals(homePageTitle, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }

    public void enterSearchTerm(String searchTerm) {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        ScrollUtility scroll = new ScrollUtility(driver);

        scroll.scrollElementIntoView(driver.findElement(searchBox));
        highlight.highlightElement(driver.findElement(searchBox));
        driver.findElement(searchBox).sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        ScrollUtility scroll = new ScrollUtility(driver);

        scroll.scrollElementIntoView(driver.findElement(searchButton));
        highlight.highlightElement(driver.findElement(searchButton));
        driver.findElement(searchButton).click();
    }

}
