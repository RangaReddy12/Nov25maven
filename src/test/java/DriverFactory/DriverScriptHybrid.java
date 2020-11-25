package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.PBFunctions;
import Constant.PBConstant;
import Utilites.ExcelFileUtil;

public class DriverScriptHybrid extends PBConstant {
String inputpath="D:\\NovemberBatchEveng\\Selenium_Maven\\TestInput\\HybridTest.xlsx";
String outputpath="D:\\NovemberBatchEveng\\Selenium_Maven\\TestOutput\\Hybrid.xlsx";
String TCSheet="TestCases";
String TSSheet="TestSteps";
ExtentReports report;
ExtentTest test;

@Test
public void startTest()throws Throwable
{
	//generate extentreport
report=new ExtentReports("./ExtenReports/"+PBFunctions.generateDate()+"Hybrid.html");	
	String tcres=null;
	boolean res=false;
	
	//create object for excel methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in TCSheet
int TCCount=xl.rowCount(TCSheet);
//count no of rows in TSSheet
int TSCount=xl.rowCount(TSSheet);
Reporter.log("No of rows in TCsheet::"+TCCount+"   "+"No of rows in TSsheet::"+TSCount,true);
for(int i=1;i<=TCCount;i++)
{
	//start test test case
		test=report.startTest("Hybrid");
	//read execute cell From TCSheet
String Execute=xl.getCellData(TCSheet, i, 2);
if(Execute.equalsIgnoreCase("Y"))
{
	//read tcid from TCSheet	
	String tcid=xl.getCellData(TCSheet, i, 0);
	for(int j=1;j<=TSCount;j++)
	{
	//read description cell
	String Description=xl.getCellData(TSSheet, j, 2);
		//read tsid cell from TSSheet
String tsid=xl.getCellData(TSSheet, j, 0);
if(tcid.equalsIgnoreCase(tsid))
{
	//read keyword cell
	String keyword=xl.getCellData(TSSheet, j, 4);
	if(keyword.equalsIgnoreCase("AdminLogin"))
	{
		String username=xl.getCellData(TSSheet, j, 5);
		String password=xl.getCellData(TSSheet, j, 6);
	res=PBFunctions.verifyLogin(username, password);
	test.log(LogStatus.INFO, Description);
	}
	else if(keyword.equalsIgnoreCase("NewBranchCreation"))
	{
	String bname=xl.getCellData(TSSheet, j, 5);	
	String add1=xl.getCellData(TSSheet, j, 6);	
	String add2=xl.getCellData(TSSheet, j, 7);
	String add3=xl.getCellData(TSSheet, j, 8);	
	String area=xl.getCellData(TSSheet, j, 9);
	String code=xl.getCellData(TSSheet, j, 10);	
	String country=xl.getCellData(TSSheet, j, 11);
	String state=xl.getCellData(TSSheet, j, 12);
	String city=xl.getCellData(TSSheet, j, 13);	
		PBFunctions.clickBranches();
		res=PBFunctions.verifyBranchcreation(bname, add1, add2, add3, area, code, country, state, city);
		test.log(LogStatus.INFO, Description);
	}
	else if(keyword.equalsIgnoreCase("UpdateBranch"))
	{
	String branch=xl.getCellData(TSSheet, j, 5);
	String address=xl.getCellData(TSSheet, j, 6);
	String zcode=xl.getCellData(TSSheet, j, 10);
		PBFunctions.clickBranches();
		res=PBFunctions.verifybranchupdate(branch, address, zcode);
		test.log(LogStatus.INFO, Description);
	}
	else if(keyword.equalsIgnoreCase("AdminLogout"))
	{
		res=PBFunctions.verifyLogout();
		test.log(LogStatus.INFO, Description);
	}
	
	String tsres="";
	if(res)
	{
		//if res is true write as pass into results cell in TSsheet
		tsres="pass";
		xl.setCellData(TSSheet, j, 3, tsres, outputpath);
		test.log(LogStatus.PASS, Description);
	}
	else
	{
		//if res is false write as fail into results cell in TSsheet
		tsres="fail";
		xl.setCellData(TSSheet, j, 3, tsres, outputpath);
		test.log(LogStatus.FAIL, Description);
	}
	tcres=tsres;
}
report.endTest(test);
report.flush();
	}
	//write tcres in TCSheet
	xl.setCellData(TCSheet, i, 3, tcres, outputpath);
}
else
{
	//write as Blocked into results cell in TCSheet
	xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
}
}
}
}
