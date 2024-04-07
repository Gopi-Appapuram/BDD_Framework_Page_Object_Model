//package StepDefinations;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.Then;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import static org.testng.Assert.assertTrue;
//
//public class WishlistSteps {
//    WebDriver driver;
//
//    @Given("I am logged in as {string}")
//    public void i_am_logged_in_as(String username) {
//
//    }
//
//    @Given("I am on the product page for {string}")
//    public void i_am_on_the_product_page_for(String product) {
//
//    }
//
//    @When("I click on the \"Add to Wishlist\" button")
//    public void i_click_on_the_Add_to_Wishlist_button() {
//
//
//    }
//
//    @Then("I should see a confirmation message")
//    public void i_should_see_a_confirmation_message() {
//
//    }
//
//    @Then("the item {string} should be added to my wishlist")
//    public void the_item_should_be_added_to_my_wishlist(String product) {
//
//    }
//
//    @Given("I have {string} in my wishlist")
//    public void i_have_in_my_wishlist(String product) {
//
//    }
//
//    @When("I navigate to my wishlist")
//    public void i_navigate_to_my_wishlist() {
//
//
//    }
//
//    @When("I remove {string} from the wishlist")
//    public void i_remove_from_the_wishlist(String product) {
//
//    }
//
//    @Then("I should see a success message")
//    public void i_should_see_a_success_message() {
//
//    }
//
//    @Then("{string} should be removed from my wishlist")
//    public void should_be_removed_from_my_wishlist(String product) {
//        assertTrue(driver.findElements(By.xpath("//span[contains(text(), '" + product + "')]")).isEmpty());
//        driver.quit();
//    }
//}
