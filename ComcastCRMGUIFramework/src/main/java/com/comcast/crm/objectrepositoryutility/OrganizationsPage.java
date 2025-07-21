package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createNewOrg;
	
	@FindBy(name="search_text")
	private WebElement searchTF;
	
	@FindBy(xpath="//input[@name='submit']")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getSearchTF() {
		return searchTF;
	}


	public WebElement getCreateNewOrg() {
		return createNewOrg;
	}
	
	
}
