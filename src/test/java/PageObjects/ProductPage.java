package PageObjects;

import UtilityClasses.ExcelUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import io.cucumber.java.mk_latn.No;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;

public class ProductPage {
    private final WebDriver driver;

    // Locators
    private final By productName = By.xpath("//span[@id='productTitle']");
    private final By productPrice = By.xpath("//div[@class='a-section a-spacing-none aok-align-center aok-relative']//span[@class='a-price-whole']");
    private final By productDiscountPercentage = By.xpath("//div[@class='a-section a-spacing-none aok-align-center aok-relative']//span[@class='aok-offscreen']");
    private final By productImageLink = By.xpath("//img[@id='landingImage']");
    private final By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");
    private final By confirmationMessageAddedToCart = By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[@class='a-alert-heading'][normalize-space()='Added to Cart']");
    private final By confirmationMessageAddedToCartIfLogout = By.xpath("//h1[normalize-space()='Added to Cart']");
    private final By closeCartMessagePanelCrossIcon = By.xpath("//a[@aria-label='Exit this panel and return to the product page. ']");




    public ProductPage(WebDriver driver) {

        this.driver = driver;
    }

    public void clickAddToCart() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        for(WebElement addToCart : driver.findElements(addToCartButton)){
            try{
                highlight.highlightElement(addToCart);
                addToCart.click();
            }catch (NoSuchElementException e){
                System.out.println(e);
            }

        }

    }


    public boolean isConfirmationMessageDisplayed() {
        WebElement confirmationMessageElement = null;

        try {
            confirmationMessageElement = driver.findElement(confirmationMessageAddedToCart);
            if (confirmationMessageElement.isDisplayed()) {
                SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
                highlight.highlightElement(confirmationMessageElement);
                String message = confirmationMessageElement.getText();
                System.out.println("Confirmation Message: " + message);
                return true;
            }
        } catch (NoSuchElementException e) {
            // Handle if the element is not found
        }

        try {
            confirmationMessageElement = driver.findElement(confirmationMessageAddedToCartIfLogout);
            if (confirmationMessageElement.isDisplayed()) {
                SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
                highlight.highlightElement(confirmationMessageElement);
                String message = confirmationMessageElement.getText();
                System.out.println("Confirmation Message (If Logged Out): " + message);
                return true;
            }
        } catch (NoSuchElementException e) {
            // Handle if the element is not found
        }

        System.out.println("Confirmation message is not displayed");
        return false;
    }

    public void closeCartMessage(){
        try {
            SeleniumHighlighterUtility highlight =new SeleniumHighlighterUtility(driver);
            highlight.highlightElement(driver.findElement(closeCartMessagePanelCrossIcon));
            driver.findElement(closeCartMessagePanelCrossIcon).click();
        }catch (NoSuchElementException e){
            System.out.println("No such division is displayed");
        }

    }

    public void getProductDetails(){
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(productName));
        String ProductName = driver.findElement(productName).getText();
        String ProductPrice = driver.findElement(productPrice).getText().replaceAll(",", "");
        String ProductDiscountPercentage = driver.findElement(productDiscountPercentage).getText();
        String ProductImageUrl = driver.findElement(productImageLink).getAttribute("src");
        Date currentDate = new Date();
        String[] ProductDetails = {
                String.valueOf(currentDate),
                ProductName,
                ProductPrice,
                ProductDiscountPercentage,
                ProductImageUrl
        };
        ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
        excel.setSheet("ProductDetailsPageData");
        excel.writeData(0, ProductDetails, "White");
        System.out.println("+--------------------------------------+");
        System.out.println("|          PRODUCT DETAILS             |");
        System.out.println("+--------------------------------------+");
        for (String product : ProductDetails) {
            System.out.println(product);
        }
        System.out.println("+--------------------------------------+");
        System.out.println();
    }
}
