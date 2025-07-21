package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class createOrganizationWithIndustriesTest_testcase2 {

	public static void main(String[] args) throws IOException, ParseException {
		//get the java representation object of the physical file
				FileInputStream fis=new FileInputStream("./configAppData/commondata.properties");
						
				//step 2:using properties class,load all the keys
				Properties pObj=new Properties();
				pObj.load(fis);
				
				//step 3:Read the data
				String browser=pObj.getProperty("browser");
				
				
				String URL=pObj.getProperty("url");
				String url1=pObj.getProperty("url1");
				String USERNAME=pObj.getProperty("username");
				String Password=pObj.getProperty("password");
				String search=pObj.getProperty("search");
        
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
		JavaUtility jLib=new JavaUtility();
		
		//get the data from excel sheet
		FileInputStream fis1=new FileInputStream("./testdata/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("Sheet1");
		
		Row row=sh.getRow(1);
		
		Cell cellImp=row.getCell(2);
		String org=cellImp.getStringCellValue().toString()+jLib.getRandomNumber();
		
		Row row1=sh.getRow(4);
		
		Cell cell=row1.getCell(3);
		String industry=cell.getStringCellValue().toString();
		
		Cell cell1=row1.getCell(4);
		String type=cell1.getStringCellValue().toString();
		
		wb.close();
        //step 1:get the url1
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url1);
		
		//step 2:give the login credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		
		//step 3:navigate to organization module
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4:enter all the details and 
		driver.findElement(By.name("accountname")).sendKeys(org);
		
		//verify industry dropdown
		WebElement industryDropdown=driver.findElement(By.name("industry"));
		Select sel=new Select(industryDropdown);
		sel.selectByVisibleText(industry);
		
		//verify type dropdown
		WebElement typeDropdown=driver.findElement(By.name("accounttype"));
		Select sel2=new Select(typeDropdown);
		sel2.selectByVisibleText(type);
		
		
		//driver.switchTo().alert().accept();  
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		
		//verify header message expected result
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(org)) {
			System.out.println(org+" is created=pass");
		}else {
			System.out.println(org+" is created=fail");
		}
		
		//verify header org info expected result
		String actualOrgName=driver.findElement(By.id("dtlview_Organization")).getText();
		if(actualOrgName.equals(org)) {
			System.out.println(org+" is created=pass");
		}else {
			System.out.println(org+" is created=fail");
		}
		
		//verify the industry type and info
		String actindustry=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if(actindustry.contains(industry)) {
			System.out.println(industry+" is created=pass");
		}else {
			System.out.println(industry+" is created=fail");
		}
		
		String actType=driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText(); 
		if(actType.contains(type)) {
			System.out.println(type+" is created=pass");
		}else {
			System.out.println(type+" is created=fail");
		}
		
		//step 5:logout
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("(//td[@class='small'])[2]"))).build().perform();
		//act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click();
		act.moveToElement(driver.findElement(By.linkText("Sign Out"))).click();
		
		
		
		driver.quit();
		


	}

}
