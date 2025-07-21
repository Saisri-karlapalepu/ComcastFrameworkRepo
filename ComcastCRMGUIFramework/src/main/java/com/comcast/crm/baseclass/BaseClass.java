package com.comcast.crm.baseclass;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseUtility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.utilityclassobject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	public DatabaseUtility dLib=new DatabaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	 @BeforeSuite(groups= {"SmokeTest","regressionTest"})
	    public void configBS() throws SQLException {
		    System.out.println("Connect to database,Report config");
	    	dLib.getDbConnection();
	    		
	    	}
	    
	    @AfterSuite(groups= {"SmokeTest","regressionTest"})
	    public void configAS() throws SQLException {
	    	System.out.println("Close the BB and Report Backup");
	    	dLib.closeDbconnection();
	    	
	    }
	   //@Parameters("Browser")
	    @BeforeClass(groups= {"SmokeTest","regressionTest"})
		public void configBC() throws IOException {
			System.out.println("Launch the browser");
			String browser=fLib.getDataFromPropertiesFile("browser");
			
			if(browser.equals("chrome")) {
				driver=new ChromeDriver();
			}
			else if(browser.equals("firefox")) {
				driver=new FirefoxDriver();
			}
			else if(browser.equals("edge")) {
				driver=new EdgeDriver();
			} 

			sdriver=driver;
			utilityclassobject.setDriver(driver);
		}
		@AfterClass(groups= {"SmokeTest","regressionTest"})
		public void configAC() {
			System.out.println("Close the browser");
			driver.quit();
		}
		@BeforeMethod(groups= {"SmokeTest","regressionTest"})
		public void configBM() throws IOException {
			System.out.println("Login to application");
			LoginPage lp=new LoginPage(driver);
			String url=fLib.getDataFromPropertiesFile("url1");
			String username=fLib.getDataFromPropertiesFile("username");
			String password=fLib.getDataFromPropertiesFile("password");
			lp.loginToApp (url,username,password);
		}
		
		@AfterMethod(groups= {"SmokeTest","regressionTest"})
		public void configAM() {
			System.out.println("Logout");
			HomePage hp=new HomePage(driver);
			hp.logout();
			
			
		}
}
