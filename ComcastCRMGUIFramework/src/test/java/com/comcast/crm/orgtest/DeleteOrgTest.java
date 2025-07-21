package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.webDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationCreatePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		//read common data from json file
		   JsonUtility jasonLib=new JsonUtility();
		  ExcelUtility eLib=new ExcelUtility();
		   JavaUtility jLib=new JavaUtility();
		   webDriverUtility wLib=new webDriverUtility();
			//read common data from JsonFile
		        
		    String url=jasonLib.getDataFromJsonFile("url");
		    String browser=jasonLib.getDataFromJsonFile("browser");
		    String username=jasonLib.getDataFromJsonFile("username");
		    String password=jasonLib.getDataFromJsonFile("password");
		        
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
				
		        //step 1:get the url1
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
				//step 2:give the login credentials
				//driver.findElement(By.name("user_name")).sendKeys(username);
				//driver.findElement(By.name("user_password")).sendKeys(password);
				//driver.findElement(By.id("submitButton")).click();
				
				//step 3:navigate to organization module
				//driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
				//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step 4:enter all the details and 
				//driver.findElement(By.name("accountname")).sendKeys(org);
				//driver.switchTo().alert().accept();  
				//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//
				//verify header message expected result
				//String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				//if(headerInfo.contains(org)) {
					//System.out.println(org+" is created=pass");
				//}else {
					//System.out.println(org+" is created=fail");
				//}
				
				//verify header org info expected result
				//String actualOrgName=driver.findElement(By.id("dtlview_Organization ")).getText();
				//if(actualOrgName.equals(org)) {
					//System.out.println(org+" is created=pass");
				//}else {
					//System.out.println(org+" is created=fail");
				//}
				
				
				//step 5:logout
				//Actions act=new Actions(driver);
				//act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
				
				//driver.findElement(By.linkText("Sign Out")).click();
		        //driver.quit();
				
				//get the data from excel sheet
				String org=eLib.getDataFromExcelSheet("Sheet1",13,2)+jLib.getRandomNumber();
				
				LoginPage lp=new LoginPage(driver);
				lp.loginToApp("admin", "admin");

             //navigate to the organization module
				HomePage hp=new HomePage(driver);
				hp.getOrgLink().click();
				
				//naviagte to campaign page
				//hp.navigateToCampaignPage();
				
				//click on "create Organizations" button
				OrganizationsPage cnp=new OrganizationsPage(driver);
				cnp.getCreateNewOrg().click();
				
				//go to organizations page and enter data and create organization
				OrganizationCreatePage of=new OrganizationCreatePage(driver);
				of.createOrg(org);
				
				OrganizationsInfoPage info=new OrganizationsInfoPage(driver);
				String actOrgname=info.getHeadermessage().getText();
				if(actOrgname.contains(org)) {
					System.out.println("Org name is verified and test case is passed");
				}
				else {
					System.out.println("Org name is not verified and test is failed");
				}
				//go back to org page
				hp.getOrgLink().click();
				
				//search for organization
				cnp.getSearchTF().sendKeys(org);
				wLib.select(driver.findElement(By.xpath("(//select[@id='bas_searchfield'])[1]")),"Organization Name");
				cnp.getSearchBtn().click();
				
				//in dynamic webtable select and delete org
				driver.findElement(By.xpath("//a[text()='"+org+"']/../../td[8]/a[text()='del']")).click();
				//handling the alert
				wLib.switchToAlertAndAccept(driver);
				
				hp.logout();
				driver.quit();
	}

	}
