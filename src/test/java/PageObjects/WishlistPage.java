package PageObjects;

import UtilityClasses.ExcelUtility;
import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class WishlistPage {
    private final WebDriver driver;

    // Locators
    private final By accountMenu = By.xpath("//a[@id='nav-link-accountList']"); //Accounts and List Element
    private final By yourAccountLink = By.xpath("//div[@id='nav-al-your-account']//a//span[text()='Your Account']");
    private final By yourOrdersLink = By.xpath("//div[@id='nav-al-your-account']//a//span[text()='Your Orders']");
    private final By yourWishListLink = By.xpath("//div[@id='nav-al-your-account']//a//span[text()='Your Wish List']");
    private final By yourRecommendationsLink = By.xpath("//div[@id='nav-al-your-account']//a//span[text()='Your Recommendations']");
    private final By yourPrimeMembership = By.xpath("//div[@id='nav-al-your-account']//a//span[text()='Your Prime Membership']");
    private final By yourPrimeVideo = By.xpath("//div[@id='nav-al-your-account']//a//span[text()='Your Prime Video']");
    private final By listOfItemNames = By.xpath("//li[@data-id='HBYN8JF7W6HL']//h2//a[contains(@id,'itemName')]");
    private final By clickAddToWishList = By.xpath("//input[@id='add-to-wishlist-button-submit']");
    private final By isAddToWishListPagePopUpDisplayed = By.xpath("//h4[text()='Add to Wish List']");
    private final By closeWishListPagePopUpButton = By.xpath("//button[@aria-label='Close']");
    private final By headerMessages = By.xpath("//div[contains(@class,'a-row')]//span[@class='a-size-medium-plus huc-atwl-header-main']");
    private final By deleteItemButton = By.xpath("//input[@name='submit.deleteItem']");
    private final By deletedMessage = By.xpath("//div[@class='a-row a-spacing-none']");

    public WishlistPage(WebDriver driver) {

        this.driver = driver;
    }

    public void clickYourWishList(){
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        ScrollUtility scroll = new ScrollUtility(driver);
        Actions actions = new Actions(driver);

        scroll.scrollElementIntoView(driver.findElement(accountMenu));
        highlight.highlightElement(driver.findElement(accountMenu));

        actions.moveToElement(driver.findElement(accountMenu)).perform();
        highlight.highlightElement(driver.findElement(yourWishListLink));
        driver.findElement(yourWishListLink).click();

    }

    public void clickOnAddToWishList() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        ScrollUtility scroll = new ScrollUtility(driver);
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(accountMenu));
        scroll.scrollElementIntoView(driver.findElement(clickAddToWishList));
        highlight.highlightElement(driver.findElement(clickAddToWishList));
        driver.findElement(clickAddToWishList).click();
    }

    public void isWishListPopUpDisplayed() {
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        try {
            highlight.highlightElement(driver.findElement(isAddToWishListPagePopUpDisplayed));
            if (driver.findElement(isAddToWishListPagePopUpDisplayed).isDisplayed()) {
                List<WebElement> message = driver.findElements(headerMessages);
                for(WebElement element: message){
                    System.out.println(element.getText() + " ");
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e);
        }
    }

    public void closeWishListPopUpPage(){
        SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
        highlight.highlightElement(driver.findElement(closeWishListPagePopUpButton));
        driver.findElement(closeWishListPagePopUpButton).click();
    }


    public void isItemsAvailable(){
        try {
            List<WebElement> ProductNames = driver.findElements(listOfItemNames);
            if(ProductNames.isEmpty()){
                System.err.println("No products are available in the wishlist");
            }else{
                System.out.println("Numbers of products in the list: " + ProductNames.size());
            }
        }catch (Exception e){
            System.err.println(e);
        }
    }

    public void getProductDetails(){
        List<WebElement> ProductNames = driver.findElements(listOfItemNames);
        for (WebElement productName : ProductNames){
            SeleniumHighlighterUtility highlight = new SeleniumHighlighterUtility(driver);
            highlight.highlightElement(productName);

            ScrollUtility scroll = new ScrollUtility(driver);
            scroll.scrollElementIntoView(productName);

            String ProductName = productName.getText();
            ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
            excel.setSheet("WishListData");
            excel.writeSingleValues(0, ProductName, "white");
            excel.close();
        }
    }

    public void isProductAvailableInWishlist(){
        List<WebElement> ProductNames = driver.findElements(listOfItemNames);
        for (WebElement products : ProductNames){
            SeleniumHighlighterUtility highlighter = new SeleniumHighlighterUtility(driver);
            highlighter.highlightElement(products);

            ScrollUtility scroll = new ScrollUtility(driver);
            scroll.scrollElementIntoView(products);

            String ProductName = products.getText();
            ExcelUtility excel = new ExcelUtility("D:\\ESoft_Solutions\\AutomationPractice\\Amazon\\src\\test\\resources\\TestData\\AmazonData.xlsx");
            excel.setSheet("WishListData");
            int Total_Rows = excel.rowCount();
            for (int i=1; i<=Total_Rows; i++){
               String productNameInExcel =  excel.getValueFromColumn(i, "Product Name");
               if(ProductName.equals(productNameInExcel)){
                   System.out.println("Product is available in wishlist");
                   break;
               }
//               else{
//                   //System.err.println("Product is not available in the wishlist");
//               }
            }
            excel.close();
        }

    }

    public void deleteSingleProduct(String productName){
        List<WebElement> ProductNames = driver.findElements(listOfItemNames);
        for (int i=0; i<=ProductNames.size(); i++){
            SeleniumHighlighterUtility highlighter = new SeleniumHighlighterUtility(driver);
            highlighter.highlightElement(driver.findElements(listOfItemNames).get(i));

            ScrollUtility scroll = new ScrollUtility(driver);
            scroll.scrollElementIntoView(driver.findElements(listOfItemNames).get(i));

            String ProductName = driver.findElements(listOfItemNames).get(i).getText();
            if(ProductName.contains(productName)){
                scroll.scrollElementIntoView(driver.findElements(deleteItemButton).get(i));
                highlighter.highlightElement(driver.findElements(deleteItemButton).get(i));

                driver.findElements(deleteItemButton).get(i).click();
                break;
            }
        }
    }

    public void isDeletedMessageDisplayed(){
        try {
            List<WebElement> messageElement = driver.findElements(deletedMessage);
            for(int i=0; i<=messageElement.size(); i+=1){
                boolean isDisplayed = driver.findElements(deletedMessage).get(i).isDisplayed();
                if(isDisplayed){
                    System.out.println("Item deleted from the wishlist");
                    break;
                }
            }
        }catch (NoSuchElementException e){
            System.out.println(e);
        }
    }
}
