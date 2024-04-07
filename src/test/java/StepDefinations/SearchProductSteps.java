package StepDefinations;

import PageObjects.HomePage;
import PageObjects.SearchPage;
import UtilityClasses.WebDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

/**
 * Step definitions for the Cucumber scenarios related to searching for products on Amazon.
 */
public class SearchProductSteps {
    WebDriver driver;


    @Before
    @Given("I opened chrome browser")
    public void i_Opened_Chrome_Browser() {
        driver = WebDriverManager.getChromeDriver();
        driver.get("https://www.amazon.in/");

    }

    /**
     * Opens the Chrome browser and navigates to the Amazon homepage.
     */
    @Given("I am on the Amazon homepage")
    public void i_am_on_the_Amazon_homepage() {
        HomePage homePage = new HomePage(driver);
        homePage.isHomePageDisplayed();
    }

    /**
     * Enters the given product name into the search bar on the Amazon homepage.
     *
     * @param product The name of the product to search for.
     */
    @When("I enter {string} into the search bar")
    public void i_enter_into_the_search_bar(String product) {
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchTerm(product);
    }

    /**
     * Clicks on the search button on the Amazon homepage.
     */
    @When("I click on the search button")
    public void i_click_on_the_search_button() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchButton();
    }

    /**
     * Verifies that the search results for the given product are displayed.
     *
     * @param product The name of the product to verify in the search results.
     */
    @Then("I should see a list of search results for {string}")
    public void i_should_see_a_list_of_search_results_for(String product) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.areSearchResultsDisplayed();
        searchPage.areAccurateSearchResultsDisplayed(product);
    }

    /**
     * Indicates that a search for a specific product has been performed.
     *
     * @param product The product for which the search has been performed.
     */
    @Given("I have searched for {string}")
    public void i_have_searched_for(String product) {
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchTerm(product);
    }

    /**
     * Applies the specified price range filter to the search results.
     *
     * @param minPrice The minimum price of products in the range.
     * @param maxPrice The maximum price of products in the range.
     */
    @When("I apply the min {string} to max {string} filter")
    public void i_apply_the_filter(String minPrice, String maxPrice) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.applyPriceFilter(minPrice, maxPrice);
    }

    /**
     * Verifies that only laptops priced under the specified amount are displayed in the search results.
     *
     * @param maxPrice The maximum price of products to be displayed in the search results.
     */
    @Then("I should see only laptops priced under {string} in the search results")
    public void i_should_see_only_laptops_priced_under_$_in_the_search_results(String maxPrice) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.areAccuratePriceFiltersApplied(maxPrice);

    }


}
