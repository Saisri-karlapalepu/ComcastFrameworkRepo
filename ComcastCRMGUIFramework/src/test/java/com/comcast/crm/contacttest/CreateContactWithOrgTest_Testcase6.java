package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.comcast.crm.generic.webdriverutility.webDriverUtility;

public class CreateContactWithOrgTest_Testcase6 {

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
        else if(browser.equals("safari")) {
        	driver=new SafariDriver();
        }
        driver.get(url);
       wlib.waitForPageToLoad(driver);
        driver.findElement(By.name("user_name")).sendKeys(username);
        driver.findElement(By.name("user_password")).sendKeys(password);
        driver.findElement(By.id("submitButton")).click();
        
        Random random=new Random();
        int randomInt=random.nextInt(1000);
        
        FileInputStream fis1=new FileInputStream("./testdata/testScriptdata.xlsx");
        Workbook wb=WorkbookFactory.create(fis1);
        Sheet sh=wb.getSheet("Sheet1");
        Row row=sh.getRow(10);
        String org=row.getCell(2).toString()+randomInt;
        String lastName=row.getCell(3).getStringCellValue().toString()+randomInt;
       
        
        //create organization
        driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org);
        
        
        driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
        
        
      //verify header message expected result
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(org)) {
			System.out.println(headerInfo+" headerInfo is created=pass");
		}else {
			System.out.println(headerInfo+" headerInfo is created=fail");
		}
		
		//verify header org info expected result
		String actualOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actualOrgName.equals(org)) {
			System.out.println(org+" is created=pass");
		}else {
			System.out.println(org+" is created=fail");
		}
		
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
				
				
	     driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		 driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		 driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		 
		 //control switch to child window
		 wlib.switchNewBrowserTo(driver,"Accounts&action");
		 driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(org);
		 driver.findElement(By.xpath("//input[@name='search']")).click();
		 wlib.switchNewBrowserTo(driver,"module=Accounts");
		 
		 //we have to use dynamic xpath
		 driver.findElement(By.xpath("//a[text()='"+org+"']")).click();
		 
		 //Switch to parent window
		 wlib.switchNewBrowserTo(driver,"Contacts&actions");
		
		 driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		 //verify the message is entered successfully or not
		 String name=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		 
		 if(name.trim().equals(org)) {
			 System.out.println(org +" is verified==pass");
		 }
		 else {
			 System.out.println(org+" is verified==fail");
		 }
		 driver.quit();
	}
	

}
