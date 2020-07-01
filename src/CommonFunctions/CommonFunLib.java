package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import Constants.HRMconstant;

public class CommonFunLib extends HRMconstant{
	/*Project Name:Orange HRM	
	 * Module Name:Verify Login
	 * Tester Name:Axel
	 * Test Date:06-02-2020
	*/
	public static boolean verifyLogin(String uname,String pass)
	{
		driver.findElement(By.xpath(p.getProperty("enterusername"))).sendKeys(uname);
		driver.findElement(By.xpath(p.getProperty("enterpassword"))).sendKeys(pass);
		driver.findElement(By.xpath(p.getProperty("login"))).click();
		if((driver.getCurrentUrl()).toLowerCase().contains("dashboard".toLowerCase()))
		{
			Reporter.log("Login Successful",true);
			return true;
		}
		else
		{
			Reporter.log("Login Failed",true);
			return false;
		}
	}
	/*Project Name:Orange HRM	
	 * Module Name:Add Employee
	 * Tester Name:Axel
	 * Test Date:06-02-2020
	*/
	public static boolean verifyAddEmployee(String fname,String lname,String id)
	{
		//click on pim menu
		driver.findElement(By.xpath(p.getProperty("navpim"))).click();
		//click on add button
		driver.findElement(By.xpath(p.getProperty("addbutton"))).click();
		driver.findElement(By.xpath(p.getProperty("firstname"))).sendKeys(fname);
		driver.findElement(By.xpath(p.getProperty("lastname"))).sendKeys(fname);
		WebElement eid=driver.findElement(By.xpath(p.getProperty("employeeid")));
		eid.clear();
		eid.sendKeys(id);
		driver.findElement(By.xpath(p.getProperty("savebutton"))).click();
		String exp="empNumber";
		String act=driver.getCurrentUrl();
		if(act.toLowerCase().contains(exp.toLowerCase()))
		{
			Reporter.log("Add Employee Successful",true);
			return true;
		}
		else
		{
			Reporter.log("Add Employee Failed",true);
			return false;
		}		
	}
	/*Project Name:Orange HRM	
	 * Module Name:Search Employee
	 * Tester Name:Axel
	 * Test Date:06-02-2020
	*/
	public static boolean verifySearhEmployee(String empname,String empid)
	{
		driver.findElement(By.xpath(p.getProperty("navpim"))).click();
		driver.findElement(By.xpath(p.getProperty("employeename"))).sendKeys(empname);
		driver.findElement(By.xpath(p.getProperty("searchid"))).sendKeys(empid);
		driver.findElement(By.xpath(p.getProperty("searchbutton"))).click();
		String msg=driver.findElement(By.xpath(p.getProperty("searchresult"))).getText();
		if(!msg.equalsIgnoreCase("No Records Found"))
		{
			Reporter.log("Employee Details Found Successful",true);
			return true;
		}
		else
		{
			Reporter.log("Employee Details Not found",true);
			return false;
		}		
	}
	/*Project Name:Orange HRM	
	 * Module Name:Search Employee
	 * Tester Name:Axel
	 * Test Date:06-02-2020
	*/
	public static boolean verifyLogout()
	{
		driver.findElement(By.xpath(p.getProperty("welcomebutton"))).click();
		driver.findElement(By.xpath(p.getProperty("logoutbutton"))).click();
		String exp1="login";
		String act1=driver.getCurrentUrl().toLowerCase();
		if(act1.contains(exp1.toLowerCase()))
		{
			Reporter.log("Logout Successful",true);
			return true;
		}
		else
		{
			Reporter.log("Logout Failed",true);
			return false;
		}		
	}
}
