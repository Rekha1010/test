package com.DemoProfject.CompanyName.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.SikuliException;

import com.DemoProfject.CompanyName.ExtentListeners.ExtentListeners;

public class FacebookPage extends BasePage{
	
	
	
	
	
	@FindBy(xpath = "//select[@title='Month']")
	WebElement month;
	
	
	@FindBy(xpath= "//select[@title='Year']")
			WebElement Year;
	
	
	public FacebookPage open(String url) {
		
		System.out.println(url);
		
		driver.get(url);
		
		return (FacebookPage) openPage(FacebookPage.class);
		
		
	}
	
	public void selectmonth () throws Exception {
		
		
		
		selectUsingVisibleText(month, "Jan", "month");
		
	    selectUsingIndex(month, 12);
		
	    selectUsingValue(month, "10");
	    selectUsingValue(Year, "2015");
	    System.out.println(getAllDropDownData(Year));
	    
		Thread.sleep(5000);

		picture();
	}

	
	
	public void skuliTest() throws SikuliException, Exception {
		Screen screen = new Screen(); 		
		
		
		Pattern image1 = new Pattern("D:\\workspace\\DemoProject\\src\\test\\resources\\testData\\fiestname.JPG");
		
		Pattern image2 = new Pattern("D:\\workspace\\DemoProject\\src\\test\\resources\\testData\\loginButton.JPG"); 
		
		
		 screen.wait(image1, 10);
		 
		 screen.type(image1, "Selenium");
		 
		 Thread.sleep(3000);
		 
		 screen.click(image2);
		 
		 
		 Thread.sleep(5000);
		 
		 
		 
		 
		 
		
		
	}
	
	
	
	
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		

		return ExpectedConditions.visibilityOf(month);
	}

	@Override
	protected void getPageScreenSot() {
		picture();
		
	}
	
	
	

}
