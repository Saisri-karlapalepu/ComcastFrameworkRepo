package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactTest_testCase4 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//create an object for property file
		FileUtility flib=new FileUtility();
				
				//step 3:Read the data
				String browser=flib.getDataFromPropertiesFile("browser");
				
				
				String URL=flib.getDataFromPropertiesFile("url");
				String url1=flib.getDataFromPropertiesFile("url1");
				String USERNAME=flib.getDataFromPropertiesFile("username");
				String Password=flib.getDataFromPropertiesFile("password");
				String search=flib.getDataFromPropertiesFile("search");

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

		//generate the random number
		Random random=new Random();
		int randomInt=random.nextInt(1000);

		//get the data from excel sheet
		ExcelUtility elib=new ExcelUtility();
		String lastname=elib.getDataFromExcelSheet("Sheet1",7 ,4)+randomInt;
		//step 1:get the url1
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url1);

		//step 2:give the login credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();


		//step 3:click on create organization button
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//step4:
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
				
		//step 5:verification of the header
		String actlastname=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actlastname.equals(lastname)) {
			System.out.println("Information is verified==Pass");
		}
		else {
			System.out.println("Information is not verified==Fail");
		}
		driver.quit();

           
	}

}
