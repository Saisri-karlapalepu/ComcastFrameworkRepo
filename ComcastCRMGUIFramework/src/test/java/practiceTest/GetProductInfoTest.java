package practiceTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider="getData")
	public void getProductInfoTest(String brandName,String ProductName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		
		if(driver.getTitle().equals("Amazon.in")) {
			driver.findElement(By.xpath("//button[text()='Continue shopping']")).click();
		}
		
		//search the product
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone",Keys.ENTER);
		
		
		//capture product info
		String x="//span[text()='"+ProductName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
	    String price=driver.findElement(By.xpath(x)).getText();
	    System.out.println(price);
	}
	
	@DataProvider
    public Object[][] getData() throws EncryptedDocumentException, IOException{
    	
    	ExcelUtility elib=new ExcelUtility();
    	int rowCount=elib.getRowCount("Amazon_Products");
    	
    	Object[][] objArr=new Object[rowCount][2];
    	
    	for(int i=0;i<rowCount;i++) {
    		for(int j=0;j<2;j++) {
    			objArr[i][j]=elib.getDataFromExcelSheet("Amazon_Products", i+1, j);
    		}}
    	
    	return objArr;
    }}


