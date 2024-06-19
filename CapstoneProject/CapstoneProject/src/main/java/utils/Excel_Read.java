package utils;


import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class Excel_Read {
    private static FileInputStream fileInputStream;
    private static Workbook workbook;
    private static Sheet sheet;

    public static void openExcel(String filePath, String sheetName) throws IOException {
        fileInputStream = new FileInputStream(filePath);
        workbook = WorkbookFactory.create(fileInputStream);
        sheet = workbook.getSheet(sheetName);
    }

    public static String getCellValue(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return cell.getStringCellValue();
    }

    public static int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

    public static void closeExcel() throws IOException {
        workbook.close();
        fileInputStream.close();
    }
}