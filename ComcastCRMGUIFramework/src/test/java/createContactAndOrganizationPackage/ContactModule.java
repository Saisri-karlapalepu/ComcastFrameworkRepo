package createContactAndOrganizationPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.webDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationCreatePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

/*
 * author Saisri
 * 
 */

public class ContactModule extends BaseClass{
	 
	@Test(groups={"SmokeTest","regressionTest"})
	public void createContact() throws EncryptedDocumentException, IOException {
		//generate the random number
				Random random=new Random();
				int randomInt=random.nextInt(1000);

				//get the data from excel sheet
				ExcelUtility elib=new ExcelUtility();
				String lastname=elib.getDataFromExcelSheet("Sheet1",7 ,4)+randomInt;
				
				//Navigate to the contact module
				HomePage hp=new HomePage(driver);
				hp.getContactLink().click();
				
				//step 3:enter all the details and create new contact
				ContactPage cp=new ContactPage(driver);
				cp.getCreateContactbtn().click();
				cp.createContact(lastname);
				cp.getSavebtn().click();
		        
				//step 4:verify header phone number into expected result
				String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
				if(actLastName.equals(lastname)) {
					System.out.println(lastname+" information is verified==pass");
			}
				else {
					System.out.println(lastname+" information is verified==Fail");
				}

	}
	@Test(groups="regressionTest")
    public void createContactWithSupportDate() throws EncryptedDocumentException, IOException {
        webDriverUtility wlib=new webDriverUtility();
		ExcelUtility eLib=new ExcelUtility();
        JavaUtility jLib=new JavaUtility();
        String lastName=eLib.getDataFromExcelSheet("Sheet1",7,4)+jLib.getRandomNumber();
	    
		String StartDate=jLib.getSystemdateyyyyddMM();
		
		String afterDateRequired=jLib.getRequiredDateyyyyMMdd(30);
	
	    
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
	    driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    
	    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
	    
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
	}
	
	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException {
		
		webDriverUtility wlib=new webDriverUtility();
		ExcelUtility eLib=new ExcelUtility();
		
        Random random=new Random();
        int randomInt=random.nextInt(5000);
        
        String lastName1=eLib.getDataFromExcelSheet("Sheet1", 10, 3);
        String org=eLib.getDataFromExcelSheet("Sheet1",10 ,2)+randomInt;
       
        
        //create organization
        
        driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org);
        
        
        driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
        
        
      //verify header message expected result
        ContactPage cp=new ContactPage(driver);
        cp.createContact(lastName1);
        
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
		 driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName1);
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
		 
		 String actHeader=cp.getHeaderMsg().getText();
		 boolean status=actHeader.contains(lastName1);
		 Assert.assertEquals(status, true);
		 
		 String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		 SoftAssert soft=new SoftAssert();
		 soft.assertEquals(actLastName, lastName1);
		
	}

}
