package com.DemoProfject.CompanyName.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.DemoProfject.CompanyName.ExtentListeners.ExtentListeners;

public class MouseHover extends BasePage {

	@FindBy(xpath = "//a[@href='https://nareshit.in/software-training/']//*")
	WebElement softwareTraining;

	@FindBy(xpath = "(//*[@id='menu-navigation']//li[4])[2]//li[3]/a")
	WebElement WeekendTraing;

	@FindBy(xpath = "//*[contains(text(),'About ')]")
	WebElement about;

	public MouseHover open(String url) {

		System.out.println(url);

		driver.get(url);

		return (MouseHover) openPage(MouseHover.class);

	}

	public void performAction() throws Exception {

		moveToElement(softwareTraining);
		picture();
		// click(WeekendTraing,"Weekend Traing");

		scrollToElement(about);

		Thread.sleep(5000);

//		 click(about,"about");
		 clickElementByJavaScript(about);

		
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(softwareTraining);
	}

	@Override
	protected void getPageScreenSot() {
		picture();

	}

}
