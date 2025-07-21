package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsInfoPage {
	
	WebDriver driver;
	public OrganizationsInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className="dvHeaderText")
	private WebElement headermessage;
	
	public WebElement getHeadermessage() {
		return headermessage;
	}

}
