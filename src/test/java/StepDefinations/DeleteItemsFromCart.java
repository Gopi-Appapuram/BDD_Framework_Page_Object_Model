package StepDefinations;

import PageObjects.CartPage;
import UtilityClasses.ExcelUtility;
import UtilityClasses.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class DeleteItemsFromCart {

    WebDriver driver = WebDriverManager.getChromeDriver();

    @Given("I have items in my shopping cart")
    public void i_have_items_in_my_shopping_cart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartIcon();
        cartPage.areProductsAvailableInCart();
    }
    String productNameToBeDeleted;
    @When("I select to delete any one item")
    public void i_select_to_delete_any_one_item() {
        CartPage cartPage = new CartPage(driver);
        ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
        excel.setSheet("CartPageData");
        Random random = new Random();
        int row_number = random.nextInt(excel.rowCount());
        productNameToBeDeleted = excel.getValueFromColumn(row_number, "Product Name");
        cartPage.deleteSingleItemFromCart(productNameToBeDeleted);
        excel.close();
    }
    @Then("the item should be removed from the cart")
    public void the_item_should_be_removed_from_the_cart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.isSelectedItemDeleted(productNameToBeDeleted);
    }

    @When("I select to delete items any {int} items")
    public void i_select_to_delete_items_and(int noOfItemsToBeDeleted) {
        deleteMultipleItems(noOfItemsToBeDeleted);
    }

    private void deleteMultipleItems(int noOfItemsToBeDeleted) {
        for(int i=1; i <= noOfItemsToBeDeleted; i++){
            i_have_items_in_my_shopping_cart();
            i_select_to_delete_any_one_item();
            the_item_should_be_removed_from_the_cart();
        }
    }

    @Then("the items should be removed from the cart")
    public void the_items_should_be_removed_from_the_cart() {

    }

    @When("I select to delete all items")
    public void i_select_to_delete_all_items() {
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteAllItemsFromCart();
    }
    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteAllItemsFromCart();
    }

}
