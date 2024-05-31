/*
import UtilityClasses.ScrollUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;


public class TableSortingTest {
    WebDriver driver;
    By tableColumnsNames = By.xpath("//th[@role='columnheader']");
    By rowCount = By.xpath("//tbody//tr[@role='row']");
    String columnDataXPath = "//tr/td[%s]";
    int columnIndex;

    @Test
    public void whenComparingTwoHashMapsWithArrayValuesUsingEquals_thenFail() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://1hk0z.csb.app/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

        LinkedHashMap<String, Integer> columnDataMap = getColumnData();
        columnIndex = columnDataMap.get("Alias");

        List<String> beforeSort = new ArrayList<>(getColumnText(By.xpath(String.format(columnDataXPath, columnIndex - 2))));
        List<String> afterSort = sortAscending(new ArrayList<>(beforeSort));

        System.out.println("Before sorting: " + beforeSort);
        System.out.println("After sorting: " + afterSort);

        System.out.println("Lists comparison result: " + compareLists(beforeSort, afterSort));

        driver.close();
    }

    private List<String> getColumnText(By xpath) {
        List<String> columnText = new ArrayList<>();
        for (WebElement element : driver.findElements(xpath)) {
            highlightAndScrollElement(element);
            String cellValue = element.getText();
            columnText.add(cellValue);
        }
        return columnText;
    }

    private void highlightAndScrollElement(WebElement element) {
        SeleniumHighlighterUtility highlighterUtility = new SeleniumHighlighterUtility(driver);
        highlighterUtility.highlightElement(element);
        ScrollUtility scrollUtility = new ScrollUtility(driver);
        scrollUtility.scrollElementIntoView(element);
    }

    public LinkedHashMap<String, Integer> getColumnData() {
        LinkedHashMap<String, Integer> columnDataMap = new LinkedHashMap<>();
        List<WebElement> headers = driver.findElements(tableColumnsNames);

        int index = 1;
        for (WebElement eachColumn : headers) {
            columnDataMap.put(eachColumn.getText(), index);
            index++;
        }

        return columnDataMap;
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

    private List<String> sortAscending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return sortedList;
    }

    private List<String> sortDescending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Comparator.reverseOrder());
        return sortedList;
    }
}
*/
