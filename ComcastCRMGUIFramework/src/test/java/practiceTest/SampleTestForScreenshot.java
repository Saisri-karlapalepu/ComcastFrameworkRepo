package practiceTest;

import java.io.IOException;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;



public class SampleTestForScreenshot {
	@Test
	public void amazonTest() throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.get("http://amazon.com");
		
		//step:1-create an object to EventFiring WebDriver
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		//step:2
		java.io.File srcFile=ts.getScreenshotAs(OutputType.FILE);
		
		FileHandler.copy(srcFile,new java.io.File("./screenshot/test.png"));
	}

}
