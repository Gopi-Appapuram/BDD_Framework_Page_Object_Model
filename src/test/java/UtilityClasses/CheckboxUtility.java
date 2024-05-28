package UtilityClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class CheckboxUtility {
    private WebDriver driver;

    public CheckboxUtility(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAllCheckboxes(By checkboxLocator) {
        return driver.findElements(checkboxLocator);
    }

    public void selectAllCheckboxes(By checkboxLocator, SeleniumHighlighterUtility highlighter, ScrollUtility scroll) {
        List<WebElement> checkboxes = getAllCheckboxes(checkboxLocator);
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroll.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public void selectNumberOfCheckboxes(By checkboxLocator, int numberOfCheckboxes, SeleniumHighlighterUtility highlighter, ScrollUtility scroll) {
        List<WebElement> checkboxes = getAllCheckboxes(checkboxLocator);
        for (int i = 0; i < numberOfCheckboxes && i < checkboxes.size(); i++) {
            WebElement checkbox = checkboxes.get(i);
            if (!checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroll.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public void selectRandomCheckboxes(By checkboxLocator, int numberOfCheckboxes, SeleniumHighlighterUtility highlighter, ScrollUtility scroll) {
        List<WebElement> checkboxes = getAllCheckboxes(checkboxLocator);
        Random random = new Random();
        for (int i = 0; i < numberOfCheckboxes; i++) {
            int randomIndex = random.nextInt(checkboxes.size());
            WebElement checkbox = checkboxes.get(randomIndex);
            if (!checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroll.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public void unselectCheckbox(WebElement checkbox, SeleniumHighlighterUtility highlighter, ScrollUtility scroll) {
        if (checkbox.isSelected()) {
            highlighter.highlightElement(checkbox);
            scroll.scrollElementIntoView(checkbox);
            checkbox.click();
        }
    }

    public void unselectAllCheckboxes(By checkboxLocator, SeleniumHighlighterUtility highlighter, ScrollUtility scroll) {
        List<WebElement> checkboxes = getAllCheckboxes(checkboxLocator);
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                highlighter.highlightElement(checkbox);
                scroll.scrollElementIntoView(checkbox);
                checkbox.click();
            }
        }
    }

    public boolean isCheckboxSelected(WebElement checkbox, SeleniumHighlighterUtility highlighter, ScrollUtility scroll) {
        highlighter.highlightElement(checkbox);
        scroll.scrollElementIntoView(checkbox);
        return checkbox.isSelected();
    }
}

