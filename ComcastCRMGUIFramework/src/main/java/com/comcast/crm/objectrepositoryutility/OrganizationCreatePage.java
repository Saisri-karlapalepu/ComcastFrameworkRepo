package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationCreatePage {
	WebDriver driver;
	public OrganizationCreatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='accountname']")
	private WebElement orgName;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgNameInput) {
		orgName.sendKeys(orgNameInput);
		saveBtn.click();	
	}
	public void createOrg(String orgNameInput,String industry) {
		orgName.sendKeys(orgNameInput);
		Select sel=new Select(industryDB);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
}
