package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.webDriverUtility;

public class CreateContactWithSupportDate_Testcase5 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        webDriverUtility wlib=new webDriverUtility();
		FileInputStream fis=new FileInputStream("./configAppData/commondata.properties");
        Properties pObj=new Properties();
        pObj.load(fis);
        String url=pObj.getProperty("url1");
        String browser=pObj.getProperty("browser");
        String username=pObj.getProperty("username");
        String password=pObj.getProperty("password");
        
        WebDriver driver=null;
        
        if(browser.equals("chrome")) {
        	driver=new ChromeDriver();
        }
        else if(browser.equals("firefox")) {
        	driver=new FirefoxDriver();
        }
        else if(browser.equals("edge")) {
        	driver=new EdgeDriver();
        }
        
        
        driver.get(url);
        driver.findElement(By.name("user_name")).sendKeys(username);
        driver.findElement(By.name("user_password")).sendKeys(password);
        driver.findElement(By.id("submitButton")).click();
        
        
        JavaUtility jLib=new JavaUtility();
        
        
        FileInputStream fis1=new FileInputStream("./testdata/testScriptdata.xlsx");
	    Workbook wb=WorkbookFactory.create(fis1);
	    Sheet sh=wb.getSheet("sheet1");
	    Row row=sh.getRow(7);
	    String lastname=row.getCell(4).getStringCellValue().toString()+jLib.getRandomNumber();
	    wb.close();
	    
	    
		String StartDate=jLib.getSystemdateyyyyddMM();
		
		String afterDateRequired=jLib.getRequiredDateyyyyMMdd(30);
	
	    
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
	    driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    
	    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
	    
	    driver.findElement(By.name("support_start_date")).clear();
	    driver.findElement(By.name("support_start_date")).sendKeys(StartDate);
	    
	    driver.findElement(By.name("support_end_date")).clear();
	    driver.findElement(By.name("support_end_date")).sendKeys(afterDateRequired);
	    
	    driver.findElement(By.xpath("(//input[contains(@value,'Save')] )[1]")).click();
	    wlib.switchToAlertAndAccept(driver);
	    
	    String actStartDate=driver.findElement(By.xpath("//input[@name='support_start_date']")).getText();
	    String actEndDate=driver.findElement(By.xpath("//input[@name='support_end_date']")).getText();
	    
	    if(actStartDate.equals(StartDate)) {
	    	System.out.println("Start Date is verified==Pass");
	    }
	    else {
	    	System.out.println("Start date is verified==fail");
	    }
	    if(actEndDate.equals(afterDateRequired)) {
	    	System.out.println("End Date is verified==Pass");
	    }
	    else {
	    	System.out.println("end date is verified==fail");
	    }
	   driver.quit();
	}

}
