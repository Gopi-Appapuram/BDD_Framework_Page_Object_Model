package UtilityClasses;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Gopi Appapuram
 * 
 * Utility class to perform operations on Excel files.
 */
public class ExcelUtility {

    private WebDriver driver;
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    /**
     * Constructor to initialize ExcelUtility with the file path to an Excel file.
     *
     * @param filePath The file path of the Excel file.
     */
    public ExcelUtility(String filePath) {
        this.filePath = filePath;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            this.workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the Excel sheet by name.
     *
     * @param sheetName The name of the sheet to set.
     */
    public void setSheet(String sheetName) {
        this.sheet = workbook.getSheet(sheetName);
        if (this.sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file.");
        }
    }

    /**
     * Set the Excel sheet by index.
     *
     * @param sheetIndex The index of the sheet to set.
     */
    public void setSheet(int sheetIndex) {
        this.sheet = workbook.getSheetAt(sheetIndex);
        if (this.sheet == null) {
            throw new IllegalArgumentException("Sheet at index " + sheetIndex + " not found in the Excel file.");
        }
    }

    /**
     * Get the total number of rows in the current Excel sheet.
     *
     * @return The total number of rows in the sheet.
     */
    public int rowCount() {

        return sheet.getLastRowNum();
    }
    /**
     * Gets the number of columns in the specified row of the Excel sheet.
     *
     * @param rowNum The row number for which to count the columns (0-based index).
     * @return The number of columns in the specified row.
     */
    public int colCount(int rowNum) {
        // Get the current row from the Excel sheet
        Row currentRow = sheet.getRow(rowNum);

        int columnCount = 0;

        // If the current row is not null, get the last cell number to count the columns
        if (currentRow != null) {
            columnCount = currentRow.getLastCellNum();
        }

        // Return the number of columns in the specified row
        return columnCount;
    }

    /**
     * Read data from a specific cell in the Excel sheet.
     *
     * @param rowNum The row number of the cell.
     * @param colNum The column number of the cell.
     * @return The data in the specified cell as a String.
     */
    public String readData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }
        return cell.toString();
    }

    /**
     * Write data to a specific cell in the Excel sheet.
     *
     * @param rowNum The row number of the cell.
     * @param colNum The column number of the cell.
     * @param data   The data to write to the cell.
     */
    public void writeDataOnSefColRow(int rowNum, int colNum, String data) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        cell.setCellValue(data);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write data to multiple cells in the Excel sheet with specified cell styling.
     *
     * @param startCol The starting column index for writing data.
     * @param data     The array of data to write to the cells.
     * @param colorName The name of the color to apply to the cell background.
     */
    public void writeData(int startCol, String[] data, String colorName) {
        int rowNum = findNextEmptyRow();

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        for (int i = 0; i < data.length; i++) {
            Cell cell = row.getCell(startCol + i);
            if (cell == null) {
                cell = row.createCell(startCol + i);
            }
            cell.setCellValue(data[i]);

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);
            style.setVerticalAlignment(VerticalAlignment.TOP);
            style.setAlignment(HorizontalAlignment.LEFT);
            style.setBorderTop(BorderStyle.MEDIUM);
            style.setBorderBottom(BorderStyle.MEDIUM);
            style.setBorderLeft(BorderStyle.MEDIUM);
            style.setBorderRight(BorderStyle.MEDIUM);
            style.setFillForegroundColor(IndexedColors.WHITE1.getIndex());

            try {
                IndexedColors indexedColor = IndexedColors.valueOf(colorName.toUpperCase());
                short colorIndex = indexedColor.getIndex();
                style.setFillForegroundColor(colorIndex);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            } catch (IllegalArgumentException e) {
                System.out.println("Color name not found: " + colorName);
            }

            cell.setCellStyle(style);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find the next empty row in the Excel sheet.
     *
     * @return The index of the next empty row.
     */
    private int findNextEmptyRow() {
        int rowNum = 0;
        while (sheet.getRow(rowNum) != null) {
            rowNum++;
        }
        return rowNum;
    }

    /**
     * Close the Excel workbook.
     */
    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
