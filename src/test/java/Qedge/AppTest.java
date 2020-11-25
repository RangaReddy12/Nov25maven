package Qedge;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilites.ExcelFileUtil;

public class AppTest {
WebDriver driver;
ExtentReports report;
ExtentTest test;
Properties p;
FileInputStream fi;
String inputpath="D:\\NovemberBatchEveng\\Selenium_Maven\\TestInput\\Employee.xlsx";
String outputpath="D:\\NovemberBatchEveng\\Selenium_Maven\\TestOutput\\Datadriven.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	//generate path 
	report=new ExtentReports("./TestReports/datadriven.html");
p= new Properties();
fi= new FileInputStream("D:\\NovemberBatchEveng\\Selenium_Maven\\PropertyFile\\Environment.properties");
p.load(fi);
if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
{
	System.out.println("Executing on Chrome browser");
	System.setProperty("webdriver.chrome.driver", "D:\\NovemberBatchEveng\\Selenium_Maven\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(p.getProperty("Url"));
	driver.manage().window().maximize();
	driver.findElement(By.xpath(p.getProperty("Objusername"))).sendKeys("Admin");
	driver.findElement(By.xpath(p.getProperty("Objpass"))).sendKeys("Qedge123!@#");
	driver.findElement(By.xpath(p.getProperty("Objlogin"))).click();
	Thread.sleep(4000);
}
else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
{
	System.out.println("Executing on firefox browser");
	System.setProperty("webdriver.gecko.driver", "D:\\NovemberBatchEveng\\Selenium_Maven\\Drivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.get(p.getProperty("Url"));
	driver.manage().window().maximize();
	driver.findElement(By.xpath(p.getProperty("Objusername"))).sendKeys("Admin");
	driver.findElement(By.xpath(p.getProperty("Objpass"))).sendKeys("Qedge123!@#");
	driver.findElement(By.xpath(p.getProperty("Objlogin"))).click();
	Thread.sleep(4000);
}
else
{
	System.out.println("Browser value is not matching");
}
}
@Test
public void verifyaddemp()throws Throwable
{
	
	// create object for excel methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in a sheet
	int rc=xl.rowCount("Emp");
	//count nof cell in a row
	int cc=xl.cellCount("Emp");
	Reporter.log("No of rows ::"+rc+"   "+"No of cells in firstrow::"+cc,true);
	for(int i=1; i<=rc;i++)
	{
		test=report.startTest("Add Employee");
String fname=xl.getCellData("Emp", i, 0);
String mname=xl.getCellData("Emp", i, 1);
String lname=xl.getCellData("Emp", i, 2);
driver.findElement(By.xpath(p.getProperty("Objpim"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("Objadd"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("Objfname"))).sendKeys(fname);
driver.findElement(By.xpath(p.getProperty("Objmname"))).sendKeys(mname);
driver.findElement(By.xpath(p.getProperty("Objlname"))).sendKeys(lname);
driver.findElement(By.xpath(p.getProperty("Objsave"))).click();
Thread.sleep(5000);
//capture employee id
String empid=driver.findElement(By.name("personal[txtEmployeeId]")).getAttribute("value");
xl.setCellData("Emp", i, 5, empid, outputpath);
String expected=fname+" "+mname+" "+lname;
String actual=driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[1]/div/h1")).getText();
if(expected.equals(actual))
{
	Reporter.log("Add Employee Success::"+expected+"    "+actual,true);
	//write an results
	xl.setCellData("Emp", i, 3, "Employee created success::"+expected+"   "+actual, outputpath);
	xl.setCellData("Emp", i, 4, "Pass", outputpath);
	test.log(LogStatus.PASS, "Employee created success::::"+expected+"    "+actual);
}
else
{
	Reporter.log("Add Employee Fail::"+expected+"    "+actual,true);
	xl.setCellData("Emp", i, 3, "Employee created Fail:::"+expected+"   "+actual, outputpath);
	xl.setCellData("Emp", i, 4, "Fail", outputpath);
	test.log(LogStatus.FAIL, "Employee created Fail::::"+expected+"    "+actual);
}
report.endTest(test);
report.flush();
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}
