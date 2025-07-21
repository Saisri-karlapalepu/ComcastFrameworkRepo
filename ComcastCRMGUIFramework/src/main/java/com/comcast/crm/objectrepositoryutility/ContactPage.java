package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement CreateContactbtn;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastname;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement savebtn;
    
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getCreateContactbtn() {
		return CreateContactbtn;
	}

	public WebElement getLastname() {
		return lastname;
	}
	
	public void createContact(String lastName) {
		getLastname().sendKeys(lastName);
		
	}

}
