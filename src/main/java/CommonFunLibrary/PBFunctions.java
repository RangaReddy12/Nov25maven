package CommonFunLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constant.PBConstant;

public class PBFunctions extends PBConstant {
public static boolean verifyLogin(String username,String password)throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objusername"))).sendKeys(username);
	driver.findElement(By.xpath(p.getProperty("Objpassword"))).sendKeys(password);
	driver.findElement(By.xpath(p.getProperty("Objlogin"))).click();
	Thread.sleep(5000);
	String expected="adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login Success:::"+expected+"    "+actual,true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail:::"+expected+"    "+actual,true);
		return false;
	}
}
//method for clicking branches
public static void clickBranches()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objbranches"))).click();
	Thread.sleep(5000);
}
//method for logout
public static boolean verifyLogout()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objlogout"))).click();
	Thread.sleep(5000);
	if(driver.findElement(By.xpath(p.getProperty("Objlogin"))).isDisplayed())
	{
		Reporter.log("Admin Logout Success",true);
		return true;
	}
	else
	{
		Reporter.log("Admin Logout Fail",true);
		return false;	
	}
}
public static boolean verifyBranchcreation(String branchn,String address1,
String address2,String address3,String areacode,String zipcode,String country,
String state,String city)throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objnewbranch"))).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath(p.getProperty("Objbname"))).sendKeys(branchn);
	driver.findElement(By.xpath(p.getProperty("Objaddress1"))).sendKeys(address1);
	driver.findElement(By.xpath(p.getProperty("Objaddress2"))).sendKeys(address2);
	driver.findElement(By.xpath(p.getProperty("Objaddress3"))).sendKeys(address3);
	driver.findElement(By.xpath(p.getProperty("Objarea"))).sendKeys(areacode);
	driver.findElement(By.xpath(p.getProperty("Objzipcode"))).sendKeys(zipcode);
	new Select(driver.findElement(By.xpath(p.getProperty("Objcountry")))).selectByVisibleText(country);
	Thread.sleep(5000);
	new Select(driver.findElement(By.xpath(p.getProperty("Objstate")))).selectByVisibleText(state);
	Thread.sleep(5000);
	new Select(driver.findElement(By.xpath(p.getProperty("Objcity")))).selectByVisibleText(city);
	Thread.sleep(5000);
	driver.findElement(By.xpath(p.getProperty("Objsubmit"))).click();
	Thread.sleep(5000);
	//capture alert message
	String  expectedbranchalert=driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String alertmessage="new branch";
	if(expectedbranchalert.toLowerCase().contains(alertmessage.toLowerCase()))
	{
	Reporter.log("Branch Updated Success::"+expectedbranchalert+"   "+alertmessage,true);
	return true;
	}
	else
	{
		Reporter.log("Branch Updated Fail::"+expectedbranchalert+"   "+alertmessage,true);
		return false;
	}
}
public static boolean verifybranchupdate(String bname,String address,String zipcode)
throws Throwable{
	driver.findElement(By.xpath(p.getProperty("Objedit"))).click();
	Thread.sleep(5000);
WebElement branchname=driver.findElement(By.xpath(p.getProperty("Objbranch")));
branchname.clear();
branchname.sendKeys(bname);
Thread.sleep(5000);
WebElement add= driver.findElement(By.xpath(p.getProperty("Objaddress")));
add.clear();
add.sendKeys(address);
Thread.sleep(5000);
WebElement zip=driver.findElement(By.xpath(p.getProperty("Objzcode")));
zip.clear();
zip.sendKeys(zipcode);
Thread.sleep(5000);
driver.findElement(By.xpath(p.getProperty("Objupdate"))).click();
Thread.sleep(5000);
String updatealert=driver.switchTo().alert().getText();
Thread.sleep(5000);
driver.switchTo().alert().accept();
Thread.sleep(5000);
String expectedalert="Branch updated";
if(updatealert.toLowerCase().contains(expectedalert.toLowerCase()))
{
	Reporter.log("Branch Update Success::"+updatealert+"    "+expectedalert,true);
	return true;
}
else
{
Reporter.log("Branch Update Fail::"+updatealert+"    "+expectedalert,true);
return false;
}
}
//method date generate
	public static String generateDate()
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(d);
	}
}
