package UtilityClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class WebTableUtility {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebTableUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LinkedHashMap<String, Integer> getColumnIndices(By columnHeadersLocator) {
        LinkedHashMap<String, Integer> columnIndices = new LinkedHashMap<>();
        List<WebElement> headers = driver.findElements(columnHeadersLocator);

        int index = 1;
        for (WebElement column : headers) {
            columnIndices.put(column.getText(), index);
            index++;
        }

        return columnIndices;
    }

    public List<String> getColumnText(By columnDataLocator) {
        List<String> columnText = new ArrayList<>();
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(columnDataLocator));

        for (WebElement element : elements) {
            String cellValue = element.getText();
            columnText.add(cellValue);
        }
        return columnText;
    }

    public boolean compareLists(List<String> list1, List<String> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<String> sortAscending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<String> sortDescending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Comparator.reverseOrder());
        return sortedList;
    }

    public void highlightAndScrollElement(WebElement element) {
        // Implement your highlight and scroll logic here
    }
}

