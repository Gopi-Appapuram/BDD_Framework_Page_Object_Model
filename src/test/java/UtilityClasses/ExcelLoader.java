package UtilityClasses;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelLoader {

    public Map<String, String> loadExcelData(String filePath) throws IOException {
        Map<String, String> dataMap = new LinkedHashMap<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(4); // Assuming data is in the first sheet

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip header row
            }
            Cell keyCell = row.getCell(0); // Assuming first cell is the key
            Cell valueCell = row.getCell(1); // Assuming second cell is the value

            String key = keyCell.getStringCellValue();
            String value = valueCell.getStringCellValue();

            dataMap.put(key, value);
        }

        workbook.close();
        fis.close();

        return dataMap;
    }

    // Example usage
    public static void main(String[] args) {
        ExcelLoader loader = new ExcelLoader();
        try {
            Map<String, String> data = loader.loadExcelData("path_to_your_excel_file.xlsx");
            System.out.println("Data from Excel:");
            data.forEach((key, value) -> System.out.println(key + " : " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
