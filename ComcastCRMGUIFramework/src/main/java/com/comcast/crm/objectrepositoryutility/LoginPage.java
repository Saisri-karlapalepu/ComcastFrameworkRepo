package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.webDriverUtility;

/**
 * @author saisri
 * 
 * contains Login page Elements and business Libraries like Login
 */
public class LoginPage extends webDriverUtility{ //Rule 1:create a seperate java class
	//Rule 2:Object creation
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    @FindBy(name="user_name")
    private WebElement usernameEdt;
    
    @FindBy(name="user_password")
    private WebElement passwordEdt;
    
    @FindBy(id="submitButton")
    private WebElement loginBtn;
    
    //Rule 4:object Encapsulation
    
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
    
    /**
     * Login to application based on user name ,password, url arguments
     * @param url
     * @param username
     * @param Password
     */
    //step 5:provide action
	public void loginToApp (String url,String username,String Password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(Password);
		loginBtn.click();
	}
	
    
}
