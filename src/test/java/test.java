
import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test {
    WebDriver driver;
    By dropdownButton = By.xpath("(//div[@class='ui dropdown selection'])[1]");
    By optionsXpath = By.xpath("(//option[text()='State'])[1]/ancestor::div[1]");

    @Test
    public void test() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://semantic-ui.com/modules/dropdown.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //driver.findElement(dropdownButton).click();
        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(dropdownButton)).build().perform();
//        actions.click(driver.findElement(dropdownButton)).build().perform();
        actions.scrollToElement(driver.findElement(dropdownButton)).build().perform();
//        driver.findElement(dropdownButton).click();

    }


    /**
     * Selects multiple values from a multi-select dropdown.
     *
     * @param dropdownLocator the By locator for the dropdown element
     * @param values          the values to select from the dropdown
     */
    public void selectMultipleValuesFromDropdown(By dropdownLocator, List<String> values) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);

        if (!select.isMultiple()) {
            throw new IllegalArgumentException("The provided dropdown is not a multi-select dropdown.");
        }

        for (String value : values) {
            select.selectByVisibleText(value);
        }
    }

    /**
     * Gets a list of all the values present in the dropdown.
     *
     * @param dropdownLocator the By locator for the dropdown element
     * @return a list of all the values present in the dropdown
     */
    public List<String> getAllDropdownValues(By dropdownLocator) {
        List<String> dropdownValues = new ArrayList<>();
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);

        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            dropdownValues.add(option.getText());
        }

        return dropdownValues;
    }


    /**
     * Selects a random number of values from a multi-select dropdown.
     *
     * @param dropdownLocator the By locator for the dropdown element
     * @param numberOfValues  the number of values to select from the dropdown
     */
    public void selectRandomNumberOfValuesFromDropdown(By dropdownLocator, int numberOfValues) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);

        if (!select.isMultiple()) {
            throw new IllegalArgumentException("The provided dropdown is not a multi-select dropdown.");
        }

        List<WebElement> options = select.getOptions();
        int totalOptions = options.size();

        if (numberOfValues > totalOptions) {
            throw new IllegalArgumentException("The number of values to select exceeds the total number of options in the dropdown.");
        }

        Random rand = new Random();
        List<Integer> selectedIndices = new ArrayList<>();

        for (int i = 0; i < numberOfValues; i++) {
            int selectedIndex;
            do {
                selectedIndex = rand.nextInt(totalOptions);
            } while (selectedIndices.contains(selectedIndex));
            selectedIndices.add(selectedIndex);
            select.selectByIndex(selectedIndex);
        }
    }

    /**
     * Selects all values from a multi-select dropdown.
     *
     * @param dropdownLocator the By locator for the dropdown element
     */
    public void selectAllValuesFromDropdownByIndex(By dropdownLocator) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);

        if (!select.isMultiple()) {
            throw new IllegalArgumentException("The provided dropdown is not a multi-select dropdown.");
        }

        List<WebElement> options = select.getOptions();
        int totalOptions = options.size();

        for (int i = 1; i < totalOptions; i++) {
            select.selectByIndex(i);
        }
    }

    /**
     * Selects all values from a multi-select dropdown.
     *
     * @param dropdownLocator the By locator for the dropdown element
     */
    public void selectAllValuesFromDropdownByVisibleText(By dropdownLocator) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);

        if (!select.isMultiple()) {
            throw new IllegalArgumentException("The provided dropdown is not a multi-select dropdown.");
        }

        List<WebElement> options = select.getOptions();
        int totalOptions = options.size();

        for (WebElement option : options) {
            String optionVisibleText = option.getText();
            select.selectByVisibleText(optionVisibleText);
        }

    }




}

