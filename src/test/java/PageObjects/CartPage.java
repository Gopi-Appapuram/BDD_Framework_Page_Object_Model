package PageObjects;

import UtilityClasses.ExcelUtility;
import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import UtilityClasses.WindowHandles;
import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import javax.swing.text.Highlighter;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class CartPage {
    WebDriver driver;

    // Locators
    private final By cartIcon = By.xpath("//a[@id='nav-cart']");
    private final By proceedToCheckoutButton = By.cssSelector(".a-button-input");
    private final By emptyCartMessage = By.xpath("//h1[contains(text(),'Cart is empty.')]");
    private final By emptyCartMessage2 = By.xpath("//div[contains(@class,'cart-is-empty')]");
    private final By ActiveProductNamesInCart = By.xpath("//div[@data-name='Active Items']//li[@class='a-spacing-mini sc-item-product-title-cont']");
    private final By productStockDetails = By.xpath("//span[contains(@class,'product-availability')]");
    private final By productImageInCart = By.xpath("//img[@class='sc-product-image']");
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
            ScrollUtility scroll = new ScrollUtility(driver);
            scroll.scrollElementIntoView(productName);
            highlight.highlightElement(productName);
            if (productName.getText().contains(product)) {
                System.out.println("The " + product + "is added to cart");
                break;
            } else {
                System.err.println("The " + product + "is not added to cart");
            }

        }
    }

    public void storeCartItemsInExcel() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        ScrollUtility scroll = new ScrollUtility(driver);

        int CartListSize = driver.findElements(ActiveProductNamesInCart).size();
        System.out.println("Items in cart: " + CartListSize);
        for (int i = 0; i < CartListSize; i++) {
            scroll.scrollElementIntoView(driver.findElements(ActiveProductNamesInCart).get(i));
            highlight.highlightElement(driver.findElements(ActiveProductNamesInCart).get(i));
            String productName = driver.findElements(ActiveProductNamesInCart).get(i).getText();

            highlight.highlightElement(driver.findElements(basePriceLocator).get(i));
            double basePrice = Double.parseDouble(driver.findElements(basePriceLocator).get(i).getText().replace(",", ""));// Assuming base price is in "$" format
            String productPrice = String.valueOf(basePrice);

            highlight.highlightElement(driver.findElements(qualityDropdown).get(i));
            Select dropdown = new Select(driver.findElements(qualityDropdown).get(i));
            String productQty = dropdown.getFirstSelectedOption().getText();

            highlight.highlightElement(driver.findElements(productStockDetails).get(i));
            String productStock = driver.findElements(productStockDetails).get(i).getText();
            String productImageUrl = driver.findElements(productImageInCart).get(i).getAttribute("src");

            double selectedQuantity = Double.parseDouble(productQty);
            //double productPriceValue = Double.parseDouble(productPrice);
            double totalProductPrice = selectedQuantity * basePrice;
            String strTotalProductPrice = String.valueOf(totalProductPrice);

            String[] cartItem = {
                    productName,
                    productPrice,
                    productQty,
                    strTotalProductPrice,
                    productImageUrl,
                    productStock
            };
            ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
            excel.setSheet("CartPageData");
            excel.writeData(0, cartItem, "White");
            excel.close();
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
        totalAmountText = totalAmountText.replace(",", ""); // Assuming total amount is in "$" format
        double totalAmount = Double.parseDouble(totalAmountText);
        if (calculatedTotal == totalAmount) {
            System.out.println("Calculated Total Price matches the Total Amount displayed: " + totalAmount);
        } else {
            System.err.println("Calculated Total Price does not match the Total Amount displayed.");
            System.err.println("Calculated Total: " + calculatedTotal);
            System.err.println("Total Amount displayed: " + totalAmount);
        }
    }

    public void switchToCartPage() {
        WindowHandles handles = new WindowHandles(driver);
        handles.switchToWindow("Amazon.in Shopping Cart");
        String pageTitle = driver.getTitle();
        assertEquals(pageTitle, "Amazon.in Shopping Cart", "You are on Cart page.");
    }

    public void areProductsAvailableInCart() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        try {
            List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
            if (productsInCart.isEmpty()) {
                try {
                    highlight.highlightElement(driver.findElement(emptyCartMessage));
                    System.err.println(driver.findElement(emptyCartMessage).getText());
                } catch (NoSuchElementException e1) {
                    try {
                        highlight.highlightElement(driver.findElement(emptyCartMessage2));
                        System.err.println(driver.findElement(emptyCartMessage2).getText());
                    } catch (NoSuchElementException e2) {
                        System.err.println("Cart is empty. Neither empty cart message found.");
                    }
                }
            } else {
                //assertFalse(false, "Products are not available in the cart");
                for (WebElement productElement : productsInCart) {
                    highlight.highlightElement(productElement);
                    String productName = productElement.getText().trim();
                    assertFalse(productName.isEmpty(), "Product name is empty for a product in the cart");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteSingleItemFromCart(String productNameToBeDeleted) {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        try {
            List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
            List<WebElement> quantityDropdowns = driver.findElements(qualityDropdown);
            if (productsInCart.isEmpty()) {
                try {
                    highlight.highlightElement(driver.findElement(emptyCartMessage));
                    System.err.println(driver.findElement(emptyCartMessage).getText());
                } catch (NoSuchElementException e1) {
                    try {
                        highlight.highlightElement(driver.findElement(emptyCartMessage2));
                        System.err.println(driver.findElement(emptyCartMessage2).getText());
                    } catch (NoSuchElementException e2) {
                        //System.err.println("Cart is empty. Neither empty cart message found.");
                    }
                }
            } else {
                //assertFalse(false, "Products are not available in the cart");
                for (int i = 0; i < productsInCart.size(); i++) {
                    ScrollUtility scroll = new ScrollUtility(driver);
                    scroll.scrollElementIntoView(productsInCart.get(i));
                    String productName = productsInCart.get(i).getText();
                    //assertFalse(productName.isEmpty(), "Product name is empty for a product in the cart");
                    if (productNameToBeDeleted.equals(productName)) {
                        scroll.scrollElementIntoView(driver.findElements(ActiveProductNamesInCart).get(i));
                        highlight.highlightElement(driver.findElements(ActiveProductNamesInCart).get(i));
                        String ProductName = driver.findElements(ActiveProductNamesInCart).get(i).getText();


                        highlight.highlightElement(driver.findElements(basePriceLocator).get(i));
                        double basePrice = Double.parseDouble(driver.findElements(basePriceLocator).get(i).getText().replace(",", ""));// Assuming base price is in "$" format
                        String productPrice = String.valueOf(basePrice);

                        highlight.highlightElement(driver.findElements(qualityDropdown).get(i));
                        Select dropdown = new Select(driver.findElements(qualityDropdown).get(i));
                        String productQty = dropdown.getFirstSelectedOption().getText();

                        highlight.highlightElement(driver.findElements(productStockDetails).get(i));
                        String productStock = driver.findElements(productStockDetails).get(i).getText();
                        String productImageUrl = driver.findElements(productImageInCart).get(i).getAttribute("src");

                        String[] deletedItem = {
                                productName,
                                productPrice,
                                productQty,
                                productImageUrl,
                                productStock
                        };
                        dropdown.selectByVisibleText("0 (Delete)");

                        ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
                        excel.setSheet("DeletedDataFromCart");
                        excel.writeData(0, deletedItem, "White");
                        excel.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void isSelectedItemDeleted(String deletedProductName) {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        try {
            List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
            if (productsInCart.isEmpty()) {
                try {
                    highlight.highlightElement(driver.findElement(emptyCartMessage));
                    System.err.println(driver.findElement(emptyCartMessage).getText());
                } catch (NoSuchElementException e1) {
                    try {
                        highlight.highlightElement(driver.findElement(emptyCartMessage2));
                        System.err.println(driver.findElement(emptyCartMessage2).getText());
                    } catch (NoSuchElementException e2) {
                        //System.err.println("Cart is empty. Neither empty cart message found.");
                    }
                }
            } else {
                boolean foundDeletedProduct = false;
                for (WebElement productElement : productsInCart) {
                    ScrollUtility scroll = new ScrollUtility(driver);
                    scroll.scrollElementIntoView(productElement);
                    highlight.highlightElement(productElement);
                    String productName = productElement.getText().trim();
                    if (!deletedProductName.equals(productName)) {
                        foundDeletedProduct = true;
                    } else {
                        foundDeletedProduct = false;
                        break;  // Exit the loop as the deleted product has been found
                    }
                }
                if (foundDeletedProduct) {
                    System.out.println(deletedProductName + " is deleted from the cart");
                } else {
                    System.err.println(deletedProductName + "is not deleted from the cart");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAllItemsFromCart() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        try {
            List<WebElement> productsInCart = driver.findElements(ActiveProductNamesInCart);
            //List<WebElement> quantityDropdowns = driver.findElements(qualityDropdown);
            if (productsInCart.isEmpty()) {
                try {
                    highlight.highlightElement(driver.findElement(emptyCartMessage));
                    System.err.println(driver.findElement(emptyCartMessage).getText());
                } catch (NoSuchElementException e1) {
                    try {
                        highlight.highlightElement(driver.findElement(emptyCartMessage2));
                        System.err.println(driver.findElement(emptyCartMessage2).getText());
                    } catch (NoSuchElementException e2) {
                        //System.err.println("Cart is empty. Neither empty cart message found.");
                    }
                }
            } else {
                //assertFalse(false, "Products are not available in the cart");
                for (int i = 0; i < productsInCart.size(); i++) {
                    ScrollUtility scroll = new ScrollUtility(driver);
                    scroll.scrollElementIntoView(productsInCart.get(i));
                    String productName = productsInCart.get(i).getText();

                    highlight.highlightElement(driver.findElements(ActiveProductNamesInCart).get(i));
                    String ProductName = driver.findElements(ActiveProductNamesInCart).get(i).getText();


                    highlight.highlightElement(driver.findElements(basePriceLocator).get(i));
                    double basePrice = Double.parseDouble(driver.findElements(basePriceLocator).get(i).getText().replace(",", ""));// Assuming base price is in "$" format
                    String productPrice = String.valueOf(basePrice);

                    highlight.highlightElement(driver.findElements(qualityDropdown).get(i));
                    Select dropdown = new Select(driver.findElements(qualityDropdown).get(i));
                    String productQty = dropdown.getFirstSelectedOption().getText();

                    highlight.highlightElement(driver.findElements(productStockDetails).get(i));
                    String productStock = driver.findElements(productStockDetails).get(i).getText();
                    String productImageUrl = driver.findElements(productImageInCart).get(i).getAttribute("src");

                    String[] deletedItem = {
                            productName,
                            productPrice,
                            productQty,
                            productImageUrl,
                            productStock
                    };

                    dropdown.selectByVisibleText("0 (Delete)");

                    ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
                    excel.setSheet("DeletedDataFromCart");
                    excel.writeData(0, deletedItem, "White");
                    excel.close();
                }
            }
    } catch(Exception e)    {
        System.out.println(e);
    }
}

}
