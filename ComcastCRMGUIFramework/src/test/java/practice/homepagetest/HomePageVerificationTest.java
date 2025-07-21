package practice.homepagetest;


import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class HomePageVerificationTest {
	@Test
	public void homepageTest(Method mtd) {
		//System.out.println(mtd.getName()+" Test Start");
		Reporter.log(mtd.getName()+" Test Start",true);
		String expctedpage="Home";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		//Hard Assert
		Assert.assertEquals(actTitle, expctedpage);
		//System.out.println(mtd.getName()+" Test ended");
		Reporter.log(mtd.getName()+" Test ended",true);
		driver.close();
	}
	@Test
	public void verifyLogoTest(Method mtd1) {
		//System.out.println(mtd1.getName()+" Test Start");
		Reporter.log(mtd1.getName()+" Test Start",true);
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status=driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		//HardAssert
		Assert.assertEquals(true, status);
		Assert.assertTrue(status);
		//System.out.println(mtd1.getName()+" Test ended ");
		Reporter.log(mtd1.getName()+" Test ended ",true);
		driver.close();
	}
     
}
