import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.awt.Color;

public class ExcelReader {

    public static List<String[]> getCredentials(String filePath) throws Exception {
        List<String[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getLastRowNum();
        for (int i = 2; i <= rowCount; i++) { // start from row 2 (skip header and first data row)
            XSSFRow row = sheet.getRow(i);
            if (row == null) continue;
            XSSFCell fromCell = row.getCell(4); // 'from' is column 4
            XSSFCell toCell = row.getCell(5);   // 'to' is column 5
            if (fromCell == null || toCell == null) continue;
            String from = fromCell.getStringCellValue();
            String to = toCell.getStringCellValue();
            data.add(new String[]{from, to});
        }

        workbook.close();
        return data;
    }

    public static void writeResult(String filePath, int rowIdx, String result) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        fis.close();
        Row row = sheet.getRow(rowIdx);
        if (row == null) row = sheet.createRow(rowIdx);
        Cell cell = row.createCell(2);
        cell.setCellValue(result);
        CellStyle style = workbook.createCellStyle();
        if ("PASS".equalsIgnoreCase(result)) {
            style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        } else {
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
        }
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
