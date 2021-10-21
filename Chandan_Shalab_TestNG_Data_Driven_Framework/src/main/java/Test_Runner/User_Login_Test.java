package Test_Runner;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Login_Page.User_Login_Page;
import User_Data_Stream.ReadExcel_Data;

public class User_Login_Test 
{
	String url = "https://demo.openmrs.org/openmrs/referenceapplication/login.page";
	String Browser_Type = "chrome";
	String Excel_File_Path = "D:\\Eclipse_Practiee\\Eclipse WorkSpace\\Chandan_Shalab_TestNG_Data_Driven_Framework\\src\\main\\java\\Data_Source\\User_Data.xlsx";
	String Excel_Sheet_Name = "User_Creds";
	User_Login_Page usrlp = new User_Login_Page();
	
	@DataProvider(name = "users_creds")
	public Object[][] Credentials() throws EncryptedDocumentException, IOException
	{
		ReadExcel_Data read = new ReadExcel_Data();
		return read.getCellData(Excel_File_Path, Excel_Sheet_Name);
	}
	
	@BeforeMethod
	public void open()
	{
		usrlp.browser(Browser_Type, url);
	}
	
	@Test(dataProvider = "users_creds")
	public void test_cases(String usrname , String UserPassword )
	{
		usrlp.user_Login(usrname, UserPassword);
	}
	
	
	@AfterMethod
	public void close_Browser() throws InterruptedException
	{
		Thread.sleep(3000);
		usrlp.driver.close();
	}
}
