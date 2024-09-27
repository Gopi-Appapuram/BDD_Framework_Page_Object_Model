package StepDefinations;

import Base.base;
import UtilityClasses.PaginationUtil;
import UtilityClasses.ScrollUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginationSteps extends base {
    private WebDriver driver; // Assume WebDriver is initialized and set up elsewhere
    private PaginationUtil paginationUtil;

    public PaginationSteps(WebDriver driver) {
        this.driver = driver;
        this.paginationUtil = new PaginationUtil(driver);
    }

    @Then("Validate pagination controls")
    public void validatePaginationControls() {
        paginationUtil.validatePagination();
    }

    @Given("I am on the page with pagination")
    public void iAmOnThePageWithPagination() {
        ScrollUtility scrollUtility = new ScrollUtility(driver);
        WebElement controls = driver.findElement(By.xpath("//div[@class= 'dt-layout-row'][2]"));
        scrollUtility.scrollElementIntoView(controls);
    }

    @When("I load the pagination controls")
    public void iLoadThePaginationControls() {
        driver.get("https://datatables.net/examples/basic_init/alt_pagination.html");
    }
}
