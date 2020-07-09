package com.DemoProfject.CompanyName.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.DemoProfject.CompanyName.ExtentListeners.ExtentListeners;

public class HomePage extends BasePage{
	
	
	

	
	
	
	
	@FindBy(xpath = "//*[contains(text(),'Sign in')]")
	WebElement signIn;
	
	
	public HomePage open(String url) {
		
		System.out.println(url);
		
		driver.get(url);
		
		return (HomePage) openPage(HomePage.class);
		
		
	}
	
	public AUTHENTICATIONPAGE clickonsign() {
		
		
		click(signIn,"sign In");
		
		
		return (AUTHENTICATIONPAGE) openPage(AUTHENTICATIONPAGE.class);
		
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		

		return ExpectedConditions.visibilityOf(signIn);
	}

	@Override
	protected void getPageScreenSot() {
		picture();
		
	}
	
	
	

}
