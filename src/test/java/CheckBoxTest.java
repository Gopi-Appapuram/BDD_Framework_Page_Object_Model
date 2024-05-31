/*

import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CheckBoxTest {
    WebDriver driver;

    private By checkBoxArea = By.xpath("//input[@type='checkbox']/ancestor::td");
    private By selectAllCheckBoxArea = By.xpath("//input[@type='checkbox']/ancestor::th");
    private By checkboxLocator = By.xpath("//td/child::div//input[@type='checkbox']");
    private By selectAllCheckboxLocator = By.xpath("//th/child::div//input[@type='checkbox']");
    SeleniumHighlighterUtility highlighter;
    ScrollUtility scroller;

    @Test
    public void testCheckBoxes() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://1hk0z.csb.app/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        highlighter = new SeleniumHighlighterUtility(driver);
        scroller = new ScrollUtility(driver);
        selectRandomCheckboxes(5);
        unselectAllCheckboxes();
        System.out.println("Is Select All Checkbox Checked: " + isSelectAllCheckboxChecked());
        driver.close();
    }

    public List<WebElement> getAllCheckboxes() {
        return driver.findElements(checkboxLocator);
    }

    public WebElement getSelectAllCheckbox() {
        return driver.findElement(selectAllCheckboxLocator);
    }

    public void selectAllCheckboxes() {
        List<WebElement> checkboxes = getAllCheckboxes();
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroller.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public void selectNumberOfCheckboxes(int numberOfCheckboxes) {
        List<WebElement> checkboxes = getAllCheckboxes();
        for (int i = 0; i < numberOfCheckboxes && i < checkboxes.size(); i++) {
            WebElement checkbox = checkboxes.get(i);
            if (!checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroller.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public void selectRandomCheckboxes(int numberOfCheckboxes) {
        List<WebElement> checkboxes = getAllCheckboxes();
        Random random = new Random();
        for (int i = 0; i < numberOfCheckboxes; i++) {
            int randomIndex = random.nextInt(checkboxes.size());
            WebElement checkbox = checkboxes.get(randomIndex);
            if (!checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroller.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public void unselectCheckbox(WebElement checkbox) {
        if (checkbox.isSelected()) {
            highlighter.highlightElement(checkbox);
            scroller.scrollElementIntoView(checkbox);
            checkbox.click();
        }
    }

    public void unselectAllCheckboxes() {
        List<WebElement> checkboxes = getAllCheckboxes();
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroller.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public boolean isSelectAllCheckboxChecked() {
        WebElement selectAllCheckbox = getSelectAllCheckbox();
        highlighter.highlightElement(selectAllCheckbox);
        scroller.scrollElementIntoView(selectAllCheckbox);
        return selectAllCheckbox.isSelected();
    }
}
*/
