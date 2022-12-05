package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	
	public static String fetchExcelSheet(String sheetName,int row,int cell) throws EncryptedDocumentException, IOException
	{
		File path = new File("D:\\Automation Practice\\KiteZerodha\\src\\test\\resources\\ZerodhaLogInCredentials.xlsx");
		FileInputStream fileInputStream = new FileInputStream(path);
		String value = WorkbookFactory.create(fileInputStream).getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
	    return value;
	}

}
