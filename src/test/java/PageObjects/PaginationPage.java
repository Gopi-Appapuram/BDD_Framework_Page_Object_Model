package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PaginationPage {
    private WebDriver driver;
    private SoftAssert softAssert;

    // By locators
    private By paginationContainer = By.xpath("//div[@class='dt-layout-row'][2]");
    private By firstButton = By.id("first");
    private By lastButton = By.id("last");
    private By prevButton = By.xpath("//button[contains(@class, 'previous')]");
    private By nextButton = By.xpath("//button[contains(@class, 'next')]");
    private By pageSizeDropdown = By.tagName("select");
    private By pageNumbers = By.cssSelector(".pagination .page-number");
    private By currentPageHighlight = By.cssSelector(".pagination .current");
    private By totalCount = By.id("totalCount");

    public PaginationPage(WebDriver driver) {
        this.driver = driver;
        this.softAssert = new SoftAssert();
    }

    public void validatePaginationControls() {
        // 1. Get the total count of records
        int totalRecords = getTotalRecordCount();

        // 2. Determine whether to show pagination based on total records
        boolean isPaginationVisible;
        try {
            isPaginationVisible = driver.findElement(paginationContainer).isDisplayed();
        } catch (NoSuchElementException e) {
            isPaginationVisible = false; // If not found, consider pagination not visible
        }

        if (totalRecords <= 10) {
            softAssert.assertFalse(isPaginationVisible, "Pagination controls should not be displayed when records are less than or equal to 10.");
            return; // Exit if pagination is not needed
        }

        // 3. Verify pagination is shown on the page load
        softAssert.assertTrue(isPaginationVisible, "Pagination controls are not displayed.");

        // 4. Verify that the default page should be selected on page load
        verifyCurrentPageHighlight(1);

        // 5. Verify page numbers
        int expectedTotalPages = verifyPageNumbers();

        // 6. Verify total count number
        verifyTotalCount(expectedTotalPages);

        // 7. Verify previous and next buttons visibility
        verifyButtonVisibility();

        // 8. Verify previous button state on the first page
        verifyButtonState(firstButton, true);
        verifyButtonState(prevButton, true);

        // 9. Check next button state on the last page
        if (expectedTotalPages > 1) {
            verifyButtonState(lastButton, true);
            verifyButtonState(nextButton, false); // Initially assuming we're on the first page
        } else {
            verifyButtonState(nextButton, true);
        }

        // 10. Check functionality of the First button
        clickButton(firstButton);
        verifyCurrentPageHighlight(1);

        // 11. Check functionality of the Last button
        clickButton(lastButton);
        verifyCurrentPageHighlight(expectedTotalPages);

        // Validate button states after navigating to last page
        if (expectedTotalPages > 1) {
            verifyButtonState(prevButton, false);
            verifyButtonState(firstButton, false);
            verifyButtonState(nextButton, true);
            verifyButtonState(lastButton, false);
        }

        softAssert.assertAll(); // Assert all at once
    }

    private int getTotalRecordCount() {
        WebElement totalCountElement = driver.findElement(totalCount);
        return Integer.parseInt(totalCountElement.getText());
    }

    private void verifyButtonState(By buttonLocator, boolean shouldBeDisabled) {
        WebElement button = driver.findElement(buttonLocator);
        boolean isDisabled = !button.isEnabled();
        softAssert.assertEquals(isDisabled, shouldBeDisabled, buttonLocator + " state mismatch.");
    }

    private void verifyCurrentPageHighlight(int currentPage) {
        WebElement highlightedElement = driver.findElement(currentPageHighlight);
        softAssert.assertEquals(Integer.parseInt(highlightedElement.getText()), currentPage, "Current page highlight mismatch.");
    }

    private int verifyPageNumbers() {
        List<WebElement> pages = driver.findElements(pageNumbers);
        int expectedTotalPages = pages.size();
        softAssert.assertTrue(expectedTotalPages > 0, "Total page numbers mismatch.");

        for (int i = 0; i < pages.size(); i++) {
            int pageNum = i + 1;
            softAssert.assertEquals(Integer.parseInt(pages.get(i).getText()), pageNum, "Page number mismatch at index " + i);
        }

        return expectedTotalPages;
    }

    private void verifyTotalCount(int expectedTotalPages) {
        WebElement totalCountElement = driver.findElement(totalCount);
        int expectedTotalCount = Integer.parseInt(totalCountElement.getText());
        softAssert.assertEquals(expectedTotalCount, expectedTotalPages * getCurrentPageSize(), "Total count mismatch.");
    }

    private int getCurrentPageSize() {
        Select dropdown = new Select(driver.findElement(pageSizeDropdown));
        return Integer.parseInt(dropdown.getFirstSelectedOption().getText());
    }

    private void verifyButtonVisibility() {
        softAssert.assertTrue(driver.findElement(prevButton).isDisplayed(), "Previous button is not displayed.");
        softAssert.assertTrue(driver.findElement(nextButton).isDisplayed(), "Next button is not displayed.");
    }

    private void clickButton(By buttonLocator) {
        driver.findElement(buttonLocator).click();
        // Add wait for UI changes if necessary
    }
}
