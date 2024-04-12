package StepDefinations;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.WishlistPage;
import UtilityClasses.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WishlistSteps {
    WebDriver driver = WebDriverManager.getChromeDriver();

    @Given("I am logged in as {string}")
    public void i_am_logged_in_as(String username) {
        LoginPage loginPage = new LoginPage(driver);
        String Welcome_Message = loginPage.isWelcomeMessageDisplayed();
        if(Welcome_Message.contains(username)){
            System.out.println("Logged in as " + username);
        }else{
            System.err.println(Welcome_Message);
        }
        assertEquals("Hello, " + username, Welcome_Message);
    }

//    @Given("I am on the product page for {string}")
//    public void i_am_on_the_product_page_for(String product) {
//
//    }

    @When("I click on the \"Add to Wishlist\" button")
    public void i_click_on_the_Add_to_Wishlist_button() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.clickOnAddToWishList();
    }

    @Then("I should see a wishlist confirmation message")
    public void i_should_see_a_wishlist_confirmation_message() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.isWishListPopUpDisplayed();
        wishlistPage.closeWishListPopUpPage();
    }

    @Then("the item should be added to my wishlist")
    public void the_item_should_be_added_to_my_wishlist() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.clickYourWishList();
        wishlistPage.isItemsAvailable();
        wishlistPage.getProductDetails();

        wishlistPage.isProductAvailableInWishlist();

    }

    @When("I navigate to my wishlist")
    public void i_navigate_to_my_wishlist() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.clickYourWishList();

    }

    @Given("I have products in my wishlist")
    public void i_have_in_my_wishlist() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.isItemsAvailable();
        wishlistPage.isProductAvailableInWishlist();
        //wishlistPage.getProductDetails();
    }

    @When("I remove {string} from the wishlist")
    public void i_remove_from_the_wishlist(String product) {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.deleteSingleProduct(product);
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.isDeletedMessageDisplayed();
    }

    @Then("{string} should be removed from my wishlist")
    public void should_be_removed_from_my_wishlist(String product) {
//        assertTrue(driver.findElements(By.xpath("//span[contains(text(), '" + product + "')]")).isEmpty());
//        driver.quit();
    }
}
