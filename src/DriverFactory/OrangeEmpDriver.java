package DriverFactory;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.CommonFunLib;
import Constants.HRMconstant;
import XmlUtility.ExcelFileUtility;

public class OrangeEmpDriver extends HRMconstant {

	String inputpath="E:\\Selenium_Evengbatch\\KeyWordDriven_Framework\\TestInput\\OrangeHRM.xlsx";
	String outputpath="E:\\Selenium_Evengbatch\\KeyWordDriven_Framework\\TestOutput\\HRMKeyWord.xlsx";
	String TCSheet="TestCases";
	String TSSheet="TestSheets";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void ohrmtest()throws Throwable
	{
		report=new ExtentReports("./ExtentReports/HRMReport.html");
		boolean res=false;
		String tcres=null;
		ExcelFileUtility xl=new ExcelFileUtility(inputpath);
		//TestCases Row Count
		int TCcount=xl.rowCount(TCSheet);
		//TestSteps Row Count
		int TScount=xl.rowCount(TSSheet);
		for(int i=1;i<=TCcount;i++)
		{
			test=report.startTest("KeyWord FrameWork of OrangeHRM");
			String execute=xl.getCellData(TCSheet, i, 2);
			if(execute.equalsIgnoreCase("Y"))
			{
				//Retrive TestCase ID from XML
				String TCid=xl.getCellData(TCSheet, i, 0);
				for(int j=1;j<=TScount;j++)
				{
					//Retrive Description from TestSheet
					String description=xl.getCellData(TSSheet, j, 2);
					//Retrive TestStep ID from XML
					String TSid=xl.getCellData(TSSheet, j, 0);
					if(TCid.equalsIgnoreCase(TSid))
					{
						//Retrive KeyWords From TSSheet
						String keyword=xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							res=CommonFunLib.verifyLogin("Admin","Qedge123!@#");
							test.log(LogStatus.INFO,description);
						}
						else if(keyword.equalsIgnoreCase("AddEmployee"))
						{
							res=CommonFunLib.verifyAddEmployee("Dipti", "Selenium", "2856");
							test.log(LogStatus.INFO,description);
						}
						else if(keyword.equalsIgnoreCase("EmployeeInformation"))
						{
							res=CommonFunLib.verifySearhEmployee("Surya", "012365");
							test.log(LogStatus.INFO,description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res=CommonFunLib.verifyLogout();
							test.log(LogStatus.INFO,description);
						}
						//Write the Result Column of TestSteps Sheet
						String tsres=null;
						if(res)
						{
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.INFO,description);
						}
						else
						{
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.INFO,description);
						}
						//A condition to store only Pass Result in TestCase Sheet Result column
						if(tsres.equalsIgnoreCase("Pass"))
						{
							tcres=tsres;
						}							
					}//tcid and tsid match condition closed
					report.endTest(test);
					report.flush();
				}//j for loop closed
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}//i for loop closed
			else
			{
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}//i for loop
	}
}
