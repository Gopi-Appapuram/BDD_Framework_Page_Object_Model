package UtilityClasses;

import org.openqa.selenium.WebDriver;
import PageObjects.PaginationPage;

public class PaginationUtil {
    private WebDriver driver;
    private PaginationPage paginationPage;

    public PaginationUtil(WebDriver driver) {
        this.driver = driver;
        this.paginationPage = new PaginationPage(driver);
    }

    public void validatePagination() {
        paginationPage.validatePaginationControls();
    }
}
