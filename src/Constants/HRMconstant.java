package Constants;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class HRMconstant {
public static WebDriver driver;
public static Properties p;
public static FileInputStream fi;
@BeforeTest
public void setup()throws Throwable
	{
		fi=new FileInputStream("E:\\Selenium_Evengbatch\\KeyWordDriven_Framework\\PropertyFile\\hrm.properties");
		p=new Properties();
		p.load(fi);
		if(p.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","E:\\Selenium_Evengbatch\\KeyWordDriven_Framework\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.get(p.getProperty("url"));
		}
		else if(p.getProperty("browser").equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","E:\\Selenium_Evengbatch\\KeyWordDriven_Framework\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS).wait();
			driver.get(p.getProperty("url"));
		}
		else
		{
			System.out.println("Please Enter Correct Browser Name");
		}
	}
@AfterTest
public void teardown()
	{
		driver.close();
	}
}
