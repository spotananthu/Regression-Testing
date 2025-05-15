import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public static List<String[]> getCredentials(String filePath) throws Exception {
        List<String[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getLastRowNum();
        for (int i = 1; i <= rowCount; i++) {
            String username = sheet.getRow(i).getCell(0).getStringCellValue();
            String password = sheet.getRow(i).getCell(1).getStringCellValue();
            data.add(new String[]{username, password});
        }

        workbook.close();
        return data;
    }
}
