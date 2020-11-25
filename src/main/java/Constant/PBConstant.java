package Constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBConstant {
public static WebDriver driver;
public static Properties p;
public static FileInputStream fi;
@BeforeMethod
public void setUp()throws Throwable
{
p=new Properties();
fi=new FileInputStream("D:\\NovemberBatchEveng\\Selenium_Maven\\PropertyFile\\Primus.properties");
p.load(fi);
if(p.getProperty("browser").equalsIgnoreCase("chrome"))
{
	System.out.println("Executing on chrome");
	System.setProperty("webdriver.chrome.driver", "D:\\NovemberBatchEveng\\Selenium_Maven\\Drivers\\chromedriver.exe");
	driver= new ChromeDriver();
	driver.get(p.getProperty("url"));
	driver.manage().window().maximize();
}
else if(p.getProperty("browser").equalsIgnoreCase("firefox"))
{
	System.out.println("Executing on firefox");
	System.setProperty("webdriver.gecko.driver", "D:\\NovemberBatchEveng\\Selenium_Maven\\Drivers\\geckodriver.exe");
	driver= new FirefoxDriver();
	driver.get(p.getProperty("url"));
}
else 
{
	System.out.println("Browser value is Not Matching");
}
}
@AfterMethod
public void tearDown()
{
driver.close();	
}
}
