package PageObjects;

import UtilityClasses.ExcelUtility;
import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class SearchPage {
    private final WebDriver driver;


    // Locators
    private final By searchResults = By.cssSelector(".s-search-results");

    private final By searchResultsNames = By.xpath("//span[contains(@class,'a-color-base a-text-normal')]");
    private final By searchResultsPrice = By.xpath("//span[contains(@class,'a-price-whole')]");
    private final By expandAllFiltersMenuLink = By.xpath("//a[contains(text(),'Expand all')]");
    private final By searchFilterByMinInput = By.xpath("//input[@id='low-price']");
    private final By searchFilterByMaxInput = By.xpath("//input[@id='high-price']");
    private final By applyPriceFilterButton = By.xpath("//input[@type='submit']/following-sibling::span[contains(text(),'Go')]");

    public SearchPage(WebDriver driver) {

        this.driver = driver;
    }


    public void areSearchResultsDisplayed() {
        if (driver.findElement(searchResults).isDisplayed()) {
            System.out.println("Product result list is displayed");
        } else {
            System.err.println("Product result list is not displayed");
        }
    }

    public void applyPriceFilter(String MinPrice, String MaxPrice) {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        ScrollUtility scroll = new ScrollUtility(driver);
        try {
            WebElement expandAllFiltersMenuLinkElement = driver.findElement(expandAllFiltersMenuLink);

            if (expandAllFiltersMenuLinkElement.isEnabled()) {
                // If element is enabled, attempt to click
                try {
                    expandAllFiltersMenuLinkElement.click();
                    System.out.println("Clicked on Expand all button");
                } catch (WebDriverException e) {
                    // Handle if the element is visible but not clickable
                    System.out.println("Expand all button is visible but not clickable.");
                    // Add further error handling or logging if needed
                }
            } else {
                // If element is not enabled, log the state
                System.out.println("Expand all button is not enabled.");
            }
        } catch (NoSuchElementException e) {
            // Handle the case where the element is not found
            System.err.println("Element is disabled and all the filters sections are expanded by default");
        }

        try{
            scroll.scrollElementIntoView(driver.findElement(searchFilterByMinInput));
            highlight.highlightElement(driver.findElement(searchFilterByMinInput));
            driver.findElement(searchFilterByMinInput).sendKeys(MinPrice);

            scroll.scrollElementIntoView(driver.findElement(searchFilterByMaxInput));
            highlight.highlightElement(driver.findElement(searchFilterByMaxInput));
            driver.findElement(searchFilterByMaxInput).sendKeys(MaxPrice);
            driver.findElement(searchFilterByMaxInput).sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e) {
            System.out.println(e);;
        }

//        scroll.scrollElementIntoView(driver.findElement(applyPriceFilterButton));
//        highlight.highlightElement(driver.findElement(applyPriceFilterButton));
//        Actions actions = new Actions(driver);
//        actions.click(driver.findElement(applyPriceFilterButton));
    }

    public void areAccurateSearchResultsDisplayed(String productName) {
        for (WebElement product : driver.findElements(searchResultsNames)) {
            ScrollUtility scroll = new ScrollUtility(driver);
            SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
            scroll.scrollElementIntoView(product);
            highlight.highlightElement(product);
            String ProductName = product.getText();
            ExcelUtility excel = new ExcelUtility("D:/ESoft_Solutions/AutomationPractice/Amazon/src/test/resources/TestData/AmazonData.xlsx");
            excel.setSheet("SearchResultsData");
            if (ProductName.contains(productName)) {
                String consoleMessage = "Results are displayed for the search keyword: " + productName;
                System.out.println(consoleMessage);
                String[] productNamesExcel = {
                        ProductName, // Column 0 in excel
                        consoleMessage // Column 1 in excel
                };
                excel.writeData(0, productNamesExcel, "white");
            } else {
                // If no match is found after checking all the search results, print a message
                String consoleMessage = "No results found for the search keyword: " + productName;
                String[] productNamesExcel = {
                        ProductName, // Column 0 in excel
                        consoleMessage // Column 1 in excel
                };
                excel.writeData(0, productNamesExcel, "white");
                System.err.println(consoleMessage);
            }
        }

    }

    public void areAccuratePriceFiltersApplied(String maxPrice) {
        for (WebElement product : driver.findElements(searchResultsPrice)) {
            ScrollUtility scroll = new ScrollUtility(driver);
            scroll.scrollElementIntoView(product);
            SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
            highlight.highlightElement(product);
            String ProductPrice = product.getText();
            // Removing commas from the string
            String cleanString = ProductPrice.replaceAll(",", "");
            int price = Integer.parseInt(cleanString);
            int MaxPrice = Integer.parseInt(maxPrice);
            if (price <= MaxPrice) {
                System.out.println("Results are shown with the cost below: " + MaxPrice);
            } else {
                // If no match is found after checking all the search results, print a message
                System.out.println("No results are shown with cost below: " + MaxPrice);
            }
        }
    }

    public void clickAnyProductFromSearchResults() {
        int listSize = driver.findElements(searchResultsPrice).size();
        Random random = new Random();
        WebElement anyOneProduct = driver.findElements(searchResultsPrice).get(random.nextInt(listSize));
        ScrollUtility scroll = new ScrollUtility(driver);
        scroll.scrollElementIntoView(anyOneProduct);
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(anyOneProduct);
        anyOneProduct.click();
    }
}

