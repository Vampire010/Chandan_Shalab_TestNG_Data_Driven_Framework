package User_Data_Stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteExcel_Data 
{
	
	public void write_me(String Username , String Password , String Result) throws EncryptedDocumentException, IOException
	{
		InputStream inp = new FileInputStream("C:\\Users\\archana\\git\\Chandan_Shalab_TestNG_Data_Driven_Framework\\Chandan_Shalab_TestNG_Data_Driven_Framework\\src\\main\\java\\Data_Source\\Login_Test_Results.xlsx");
		
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sh = wb.getSheet("");
		
		int num = sh.getLastRowNum();
		Row row = sh.createRow(++num);
		
		Map<Integer,Object[]> data = new TreeMap<Integer,Object[]>();
		data.put(1, new Object[] {"USERNAME","PASSWORD"});
		data.put(2, new Object[] {Username ,Password, Result});
		
		Set<Integer> keySet = data.keySet();
		int rownum = 1;
		
		for(Integer Key : keySet)
		{
			Object[] objArr =  data.get(Key);
			int  cellnum = 0;
			for(Object obj: objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
				{
					cell.setCellValue((String)obj);
				}
				else if(obj instanceof Integer)
				{
					cell.setCellValue((Integer)obj);
				}	
			}	
		}
		
		try
		{
			FileOutputStream filout= new FileOutputStream("C:\\\\Users\\\\archana\\\\git\\\\Chandan_Shalab_TestNG_Data_Driven_Framework\\\\Chandan_Shalab_TestNG_Data_Driven_Framework\\\\src\\\\main\\\\java\\\\Data_Source\\\\Login_Test_Results.xlsx");
			wb.write(filout);
			filout.close();
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
		
	}

}
