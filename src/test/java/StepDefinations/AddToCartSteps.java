package StepDefinations;

import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;
import PageObjects.SearchPage;
import UtilityClasses.WebDriverManager;
import UtilityClasses.WindowHandles;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class AddToCartSteps {

    WebDriver driver = WebDriverManager.getChromeDriver();

    @Given("I am on the product page for {string}")
    public void i_am_on_the_product_page_for(String product) {
        HomePage homePage =new HomePage(driver);
        homePage.enterSearchTerm(product);
        homePage.clickSearchButton();
        SearchPage searchPage = new SearchPage(driver);
        searchPage.areSearchResultsDisplayed();
        searchPage.clickAnyProductFromSearchResults();
        String windowHandle = driver.getWindowHandle();
        WindowHandles handles = new WindowHandles(driver);
        handles.switchToWindow(windowHandle);
        ProductPage productPage = new ProductPage(driver);
        productPage.getProductDetails();
    }

    @When("I click on the \"Add to Cart\" button")
    public void i_click_on_the_Add_to_Cart_button() {
        ProductPage productPage =new ProductPage(driver);
        productPage.clickAddToCart();
    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() {
        ProductPage productPage = new ProductPage(driver);
        boolean isMessageDisplayed = productPage.isConfirmationMessageDisplayed();
        assertTrue(isMessageDisplayed, "Confirmation message is displayed");
        productPage.closeCartMessage();
    }

    @Then("the item {string} should be added to my cart")
    public void the_item_should_be_added_to_my_cart(String product) {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartIcon();
        cartPage.isSelectedProductAddedToCart(product);

    }

    @Given("I have {string} in my cart")
    public void i_have_in_my_cart(String itemList) {
        String[] items = itemList.split(",");
        for (String item : items) {
            HomePage homePage = new HomePage(driver);
            homePage.enterSearchTerm(item);
            homePage.clickSearchButton();
            SearchPage searchPage = new SearchPage(driver);
            searchPage.clickAnyProductFromSearchResults();
            String windowHandle = driver.getWindowHandle();
            WindowHandles handles = new WindowHandles(driver);
            handles.switchToWindow(windowHandle);
            ProductPage productPage = new ProductPage(driver);
            productPage.getProductDetails();
            productPage.clickAddToCart();
            productPage.isConfirmationMessageDisplayed();
            productPage.closeCartMessage();
        }
    }

    @When("I increase the quantity of {string} to {int}")
    public void i_increase_the_quantity_of_to(String item, int quantity) {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartIcon();
        cartPage.isSelectedProductAddedToCart(item);
        cartPage.incOrDecCartQtyOfProduct(item,quantity);
    }

    @Then("I should see {int} {string} in my cart")
    public void i_should_see_in_my_cart(int quantity, String item) {
        CartPage cartPage = new CartPage(driver);
        cartPage.isQuantityEffectedForProduct(item, quantity);
    }

    @Then("the total price should reflect the updated quantity")
    public void the_total_price_should_reflect_the_updated_quantity() {
        CartPage cartPage = new CartPage(driver);
        double calculatedTotalPrice = cartPage.calculateTotalPriceInCart();
        cartPage.compareTotalPriceWithDisplayedAmount(calculatedTotalPrice);

    }
    @Given("I am on cart page")
    public void i_am_on_cart_page() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartIcon();
        cartPage.switchToCartPage();

    }
    @When("I have items in the cart")
    public void i_have_items_in_the_cart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.areProductsAvailableInCart();
    }
    @Then("I will store the details in the excel sheet")
    public void i_will_store_the_details_in_the_excel_sheet() {
        CartPage cartPage = new CartPage(driver);
        cartPage.storeCartItemsInExcel();
    }
}

