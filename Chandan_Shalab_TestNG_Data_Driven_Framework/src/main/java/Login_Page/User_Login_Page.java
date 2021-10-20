package Login_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Browser_Config.Browser_Launcher;

public class User_Login_Page extends Browser_Launcher
{
	public void user_Login(String Username , String Password)
	{
		
		WebElement usrname = driver.findElement(By.id("username"));
		usrname.sendKeys(Username);
		
		WebElement usrpsd = driver.findElement(By.id("password"));
		usrpsd.sendKeys(Password);
		
		
		WebElement Rgst_loc = driver.findElement(By.id("Registration Desk"));
		Rgst_loc.click();

		WebElement Login_btn = driver.findElement(By.id("loginButton"));
		Login_btn.click();
	}

}
