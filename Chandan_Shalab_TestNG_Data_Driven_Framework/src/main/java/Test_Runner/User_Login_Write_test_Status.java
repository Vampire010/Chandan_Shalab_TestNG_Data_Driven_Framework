package Test_Runner;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Login_Page.User_Login_Page;
import User_Data_Stream.ReadExcel_Data;
import User_Data_Stream.WriteExcel_Data;

public class User_Login_Write_test_Status 
{
	String url = "https://demo.openmrs.org/openmrs/referenceapplication/login.page";
	String Browser_Type = "chrome";
	String Excel_File_Path = "D:\\Eclipse_Practiee\\Eclipse WorkSpace\\Chandan_Shalab_TestNG_Data_Driven_Framework\\src\\main\\java\\Data_Source\\ttttt.xlsx";
	String Excel_Sheet_Name = "Sheet1";
	User_Login_Page usrlp = new User_Login_Page();
	WriteExcel_Data wrt_exl_data = new WriteExcel_Data();
	
	
	
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
	public void test_cases(String usrname , String UserPassword ) throws EncryptedDocumentException, IOException
	{
		usrlp.user_Login(usrname, UserPassword);
		
		String MyString = usrlp.driver.getCurrentUrl(); 
		
		System.out.println(MyString + " Im Here");
		
		String YourString = "https://demo.openmrs.org/openmrs/referenceapplication/home.page";
		if (MyString.equals(YourString))
				{
						Assert.assertTrue(YourString.equals(MyString),"Assert True is failed!" );
						System.out.println("AssertTrue Test is Passed!\n");
						wrt_exl_data.write_me(usrname, UserPassword, "PASS");
				}
				else
				{
					Assert.assertFalse(YourString.equals(MyString), "AssertFalse  is failed!");
					System.out.println("AssertFalse Test is Passed!\n");
					wrt_exl_data.write_me(usrname, UserPassword, "Fail");
				}
		
	}
	
	
	@AfterMethod
	public void close_Browser() throws InterruptedException
	{
		Thread.sleep(3000);
		usrlp.driver.close();
	}

}
