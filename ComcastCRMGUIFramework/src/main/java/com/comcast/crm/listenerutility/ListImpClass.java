package com.comcast.crm.listenerutility;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.generic.webdriverutility.utilityclassobject;

public class ListImpClass implements ITestListener,ISuiteListener{
    
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		//ISuiteListener.super.onStart(suite);
		System.out.println("Report configuration");
		//spark report configuration
		String time=new Date().toString().replace(" ","_").replace(":","_");
    	ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
    	spark.config().setDocumentTitle("CRM Test suite results");
    	spark.config().setReportName("CRM Report");
    	spark.config().setTheme(Theme.DARK);
    				
    	//add environment information and create test
    	report=new ExtentReports();
    	report.attachReporter(spark);
    	report.setSystemInfo("OS", "Windows-10");
    	report.setSystemInfo("BROWSER", "CHROME-100");

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		//ISuiteListener.super.onFinish(suite);
		System.out.println("Report bckup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		System.out.println("========"+result.getMethod().getMethodName()+"============"+"Start");
		test=report.createTest(result.getMethod().getMethodName());
		utilityclassobject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName()+"==> STARTED <==");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println("========"+result.getMethod().getMethodName()+"============"+"End");
		test.log(Status.INFO, result.getMethod().getMethodName()+"==> COMPLETED <==");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		String testName=result.getMethod().getMethodName();
		BaseClass bs=new BaseClass();
		WebDriver driver=bs.sdriver;
		
		//step:1-create an object to EventFiring WebDriver
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String filePath=ts.getScreenshotAs(OutputType.BASE64);
		
		//step:2
		java.io.File srcFile=ts.getScreenshotAs(OutputType.FILE);
		String time=new Date().toString().replace(" ","_").replace(":","_");
		
		test.addScreenCaptureFromBase64String(filePath,testName+"_"+time);
		
		//try {
			//FileHandler.copy(srcFile,new java.io.File("./screenshot/+"+testName+"+time+"+".png"));
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		test.log(Status.FAIL, "==> FAILED <==");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		test.log(Status.FAIL, "==> SKIPPED <==");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	

}
