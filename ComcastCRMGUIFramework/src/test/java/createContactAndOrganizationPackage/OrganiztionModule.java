package createContactAndOrganizationPackage;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.utilityclassobject;
import com.comcast.crm.generic.webdriverutility.webDriverUtility;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationCreatePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class OrganiztionModule extends BaseClass{
	@Test(groups="SmokeTest")
	public void createOrg() throws EncryptedDocumentException, IOException {
		 
		     ExcelUtility elib=new ExcelUtility();
		     JavaUtility jlib=new JavaUtility();
		       
			//get the data from excel sheet
			String org=elib.getDataFromExcelSheet("Sheet1",1,2)+jlib.getRandomNumber();
			   
             //navigate to the organization module
				HomePage hp=new HomePage(driver);
				hp.getOrgLink().click();
				
				//navigate to campaign page
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
	}
	
	
	@Test(groups="regressionTest")
	public void createOrganizationWithIndustries() throws EncryptedDocumentException, IOException {
		       
		       //generate the random number
				JavaUtility jLib=new JavaUtility();
				HomePage hp=new HomePage(driver);
			    ExcelUtility elib=new ExcelUtility();
			    
			    String org=elib.getDataFromExcelSheet("Sheet1", 1, 2)+jLib.getRandomNumber();
				String industry=elib.getDataFromExcelSheet("Sheet1", 4, 3);
				String type=elib.getDataFromExcelSheet("Sheet1", 4, 4);
				
				
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
				
				//
				//verify header message expected result
				String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(org)) {
					System.out.println(org+" is created=pass");
				}else {
					System.out.println(org+" is created=fail");
				}
				
				//verify header org info expected result
				String actualOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actualOrgName.equals(org)) {
					System.out.println(org+" is created=pass");
				}else {
					System.out.println(org+" is created=fail");
				}
				
				//verify the industry type and info
				String actindustry=driver.findElement(By.id("mouseArea_Industry")).getText();
				if(actindustry.contains(industry)) {
					System.out.println(industry+" is created=pass");
				}else {
					System.out.println(industry+" is created=fail");
				}
				
				//webDriverUtility wlib=new webDriverUtility();
				//wlib.switchToAlertAndAccept(driver);
				
				
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
				
	}
	
	@Test(groups="regressionTest")
	public void createOrganizationWithPhoneNumber() throws EncryptedDocumentException, IOException {
        HomePage hp=new HomePage(driver);
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		webDriverUtility wLib=new webDriverUtility();
		
		String org=eLib.getDataFromExcelSheet("Sheet1", 1, 2)+jLib.getRandomNumber();
		String industry=eLib.getDataFromExcelSheet("Sheet1", 4, 3);
		String type=eLib.getDataFromExcelSheet("Sheet1", 4, 4);
		String phoneNo=eLib.getDataFromExcelSheet("Sheet1", 7, 3);
	
		        //step 3:navigate to organization module
		     // ListImpClass.test.log(Status.INFO,"login to the organization module");
		utilityclassobject.getTest().log(Status.INFO, "login to the organization module");
				driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step 4:enter all the details and 
				driver.findElement(By.name("accountname")).sendKeys(org);
				
				//verify industry dropdown
				//ListImpClass.test.log(Status.INFO,"selecting industry from dropdown");
				utilityclassobject.getTest().log(Status.INFO, "selecting industry from dropdown");
				WebElement industryDropdown=driver.findElement(By.name("industry"));
				wLib.select(industryDropdown,industry);
				
				//verify type dropdown
				WebElement typeDropdown=driver.findElement(By.name("accounttype"));
				wLib.select(typeDropdown,type);
				//ListImpClass.test.log(Status.INFO,"selecting type from dropdown");
				utilityclassobject.getTest().log(Status.INFO, "selecting type from dropdown");
				
				driver.findElement(By.id("phone")).sendKeys(phoneNo);
				
				driver.findElement(By.xpath("(//input[contains(@value, 'Save')])[1]")).click();
				
				String phoneNumber=driver.findElement(By.id("dtlview_Phone")).getText();
				if(phoneNumber.equals(phoneNo)) {
					System.out.println("The phone number "+phoneNo+" is successfully encoded");
				}
				else {
					System.out.println("Phone number is not encoded");
				}
				
				String actindustry=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
				if(actindustry.equals(industry)) {
					System.out.println(industry+" is created=pass");
				}else {
					System.out.println(industry+" is created=fail");
				}
				
				String actType=driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText(); 
				if(actType.equals(type)) {
					System.out.println(type+" is created=pass");
				}else {
					System.out.println(type+" is created=fail");
				}
				
	}

}