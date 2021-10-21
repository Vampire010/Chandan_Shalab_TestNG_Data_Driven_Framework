package User_Data_Stream;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel_Data 
{
	
	public String[][] getCellData(String path, String sheetName) throws  IOException 
	  {
			  FileInputStream stream = new FileInputStream(path);
			  
			  Workbook workbook = WorkbookFactory.create(stream);
			  
			  Sheet s = workbook.getSheet(sheetName);
			  
			  int rowcount = s.getLastRowNum();
			  
			  int cellcount = s.getRow(0).getLastCellNum();
			  
			  String data[][] = new String[rowcount][cellcount];
			
			  for (int i = 0; i < rowcount; i++) 
			  {
				   Row r = s.getRow(i);
				   
				   for (int j = 0; j<cellcount; j++) 
				   {
				   Cell c = r.getCell(j);
						try 
						{
							if (c.getCellType() == CellType.STRING)
							{
							      data[i ][j] = c.getStringCellValue();
							} 
							else
							 {
							      data[i ][j] = String.valueOf(c.getNumericCellValue());
							} 
						} 
						catch (Exception e) 
						{
					     e.printStackTrace();
						}
			}
	  }
	  return data;
	 }
	
}
