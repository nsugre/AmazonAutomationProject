package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Reader {

	@DataProvider(name = "cred")
	public String[][] getData() throws IOException {

		// Map<String, String> users = new LinkedHashMap<>();

		String[][] users;

		File file = new File("C:\\Users\\hp\\OneDrive\\Documents\\users.xlsx");

		FileInputStream fis = new FileInputStream(file);

		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheetAt(0);

		users = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		int i = 0;

		String key, value;
		for (Row row : sheet) {

			if (row.getRowNum() == 0)
				continue;
			key = row.getCell(0).getStringCellValue();
			value = row.getCell(1).getStringCellValue();

			int j = 0;
			users[i][j++] = key;
			users[i][j] = value;
			i++;
		}

		fis.close();

		return users;

	}

}
