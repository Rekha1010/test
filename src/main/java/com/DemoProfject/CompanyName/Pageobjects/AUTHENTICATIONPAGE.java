package com.DemoProfject.CompanyName.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AUTHENTICATIONPAGE extends BasePage {

	
	
	
	
	@FindBy (xpath= "//*[@id='email']")	
	WebElement email;
    @FindBy (xpath= "//*[@id='passwd']")	
	WebElement passwd;
    @FindBy (xpath= "//*[@class='icon-lock left']")
    WebElement signInButton;
	
	
	
	public void login(String email, String password) {

		this.email.sendKeys(email);		
		passwd.sendKeys(password);
		signInButton.click();
	
	
	
	}
	
	
	
	public void Invalidlogin(String email, String password) {
		login(email, password);	
	}
	
	public void Emailverification(String email, String password) {
		login(email, password);		
		}
	
	
	public void suspended(String email, String password) {
		login(email, password);		
		}
	
	public void validLogin(String email, String password) {
		login(email, password);		
		}



	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(email);
	}



	@Override
	protected void getPageScreenSot() {
		picture();
		
	}
	
}
