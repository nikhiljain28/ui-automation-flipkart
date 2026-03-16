package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pojo.RequestPojo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataReader {

	public static List<RequestPojo> getUserData(String filePath, String sheetName) throws Exception {

		List<RequestPojo> userList = new ArrayList<>();

		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();  // total rows excluding header

		for (int i = 1; i <= rowCount; i++) {   // i=1 skips header row
			Row row = sheet.getRow(i);

			// Read each cell and map to POJO
			int id              = (int) row.getCell(0).getNumericCellValue();
			String username     = row.getCell(1).getStringCellValue();
			String firstName    = row.getCell(2).getStringCellValue();
			String lastName     = row.getCell(3).getStringCellValue();
			String email        = row.getCell(4).getStringCellValue();
			String password     = row.getCell(5).getStringCellValue();
			String phone        = row.getCell(6).getStringCellValue();
			int userStatus      = (int) row.getCell(7).getNumericCellValue();
			RequestPojo user = new RequestPojo(
					id, username, firstName, lastName,
					email, password, phone, userStatus
					);

			userList.add(user);
		}

		workbook.close();
		fis.close();
		return userList;
	}
}
