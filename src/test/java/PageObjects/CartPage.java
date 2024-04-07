package PageObjects;

import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CartPage {
    private final WebDriver driver;

    // Locators
    private final By cartIcon = By.xpath("//a[@id='nav-cart']");
    private final By proceedToCheckoutButton = By.cssSelector(".a-button-input");
    private final By ActiveProductNamesInCart = By.xpath("//div[@data-name='Active Items']//li[@class='a-spacing-mini sc-item-product-title-cont']");
    private final By qualityDropdown = By.xpath("//select[@id='quantity']");
    private final By basePriceLocator = By.xpath("//span[contains(@class,'product-price')]");
    private final By totalAmountLocator = By.xpath("//span[@id='sc-subtotal-amount-activecart']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartIcon() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(cartIcon));
        driver.findElement(cartIcon).click();
    }

    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    public void isSelectedProductAddedToCart(String product) {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        for (WebElement productName : driver.findElements(ActiveProductNamesInCart)) {
            highlight.highlightElement(productName);
            if (productName.getText().contains(product)) {
                System.out.println("The " + product + "is added to cart");
                break;
            } else {
                System.err.println("The " + product + "is not added to cart");
            }

        }
    }

    public void incOrDecCartQtyOfProduct(String product, int qty) {
        List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
        List<WebElement> quantityDropdowns = driver.findElements(qualityDropdown);

        // Loop through each product in the cart to find the matching product
        for (int i = 0; i < productsInCart.size(); i++) {
            WebElement productElement = productsInCart.get(i);

            if (productElement.getText().contains(product)) {
                // If the product matches, get the corresponding quantity dropdown
                WebElement dropdownElement = quantityDropdowns.get(i);

                // Create a new Select object for the dropdown
                Select dropdown = new Select(dropdownElement);

                // Convert the quantity to a string
                String quantity = String.valueOf(qty);

                // Select the quantity from the dropdown by visible text
                dropdown.selectByVisibleText(quantity);

                System.out.println("Quantity for " + product + " updated to " + quantity);
            }
        }
    }


    public void isQuantityEffectedForProduct(String product, int expectedQuantity) {
        List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
        List<WebElement> quantityDropdowns = driver.findElements(qualityDropdown);

        // Loop through each product in the cart to find the matching product
        for (int i = 0; i < productsInCart.size(); i++) {
            WebElement productElement = productsInCart.get(i);
            WebElement dropdownElement = quantityDropdowns.get(i);

            if (productElement.getText().contains(product)) {
                // If the product matches, get the selected quantity from the dropdown
                Select dropdown = new Select(dropdownElement);
                WebElement selectedOption = dropdown.getFirstSelectedOption();
                String selectedQuantityText = selectedOption.getText();

                // Convert the selected quantity to an integer
                int selectedQuantity = Integer.parseInt(selectedQuantityText);

                // Verify if the selected quantity matches the expected quantity
                if (selectedQuantity == expectedQuantity) {
                    System.out.println("Quantity for " + product + " is correctly updated to " + selectedQuantity);
                } else {
                    System.err.println("Quantity for " + product + " is not updated correctly. Expected: " + expectedQuantity + ", Actual: " + selectedQuantity);
                }
                return; // Exit the method once the product is found and checked
            }
        }

        // If the product is not found in the cart
        System.err.println("Product " + product + " not found in the cart.");
    }


    public double calculateTotalPriceInCart() {
        List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
        List<WebElement> quantityDropdowns = driver.findElements(qualityDropdown);
        List<WebElement> basePriceElements = driver.findElements(basePriceLocator);

        double totalPrice = 0.0;

        for (int i = 0; i < productsInCart.size(); i++) {
            WebElement productElement = productsInCart.get(i);
            WebElement dropdownElement = quantityDropdowns.get(i);
            WebElement basePriceElement = basePriceElements.get(i);

            // Get the product name, quantity, and base price
            String productName = productElement.getText();
            Select dropdown = new Select(dropdownElement);
            String selectedQuantityText = dropdown.getFirstSelectedOption().getText();
            int quantity = Integer.parseInt(selectedQuantityText);
            double basePrice = Double.parseDouble(basePriceElement.getText().replace(",", ""));// Assuming base price is in "$" format

            // Calculate the subtotal for this product and add it to the total price
            double subtotal = quantity * basePrice;
            totalPrice += subtotal;

            System.out.println("Subtotal for " + productName + " (" + quantity + " items): " + subtotal);
        }

        System.out.println("Total Price of Products in Cart: " + totalPrice);
        return totalPrice;
    }

    public void compareTotalPriceWithDisplayedAmount(double calculatedTotal) {
        WebElement totalAmountElement = driver.findElement(totalAmountLocator);
        String totalAmountText = totalAmountElement.getText();
        totalAmountText = totalAmountText.trim();
        totalAmountText = totalAmountText.replace(",",""); // Assuming total amount is in "$" format
        double totalAmount = Double.parseDouble(totalAmountText);
        if (calculatedTotal == totalAmount) {
            System.out.println("Calculated Total Price matches the Total Amount displayed: " + totalAmount);
        } else {
            System.err.println("Calculated Total Price does not match the Total Amount displayed.");
            System.err.println("Calculated Total: " + calculatedTotal);
            System.err.println("Total Amount displayed: " + totalAmount);
        }
    }

}
