package com.DemoProfject.CompanyName.Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DemoProfject.CompanyName.ExtentListeners.ExtentListeners;
import com.DemoProfject.CompanyName.ExtentListeners.ExtentManager;
import com.DemoProfject.CompanyName.Utilities.DriverCapabilities;
import com.DemoProfject.CompanyName.Utilities.DriverManager;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import org.openqa.selenium.Capabilities;


import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public abstract class BasePage<T> {
	
	
	
protected WebDriver driver;
	
	
	private long LOAD_TIMEOUT = 30;
	private int AJAX_ELEMENT_TIMEOUT = 150;
	public int expTime = 60;
	
	protected WebDriverWait wait;
	protected Capabilities caps;

	public BasePage() {
		this.driver = DriverManager.getDriver();
		this.caps = DriverCapabilities.getCapabilities();
	
//		this.wait = waitHelper.getWebDriverWaitObject();
		
	}
	
	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = PageFactory.initElements(driver, clazz);
			PageFactory.initElements(ajaxElemFactory, page);
			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
			waitForPageToLoad(pageLoadCondition);
			((BasePage) page).getPageScreenSot();
			
		} catch (NoSuchElementException e) {
			/*
			 * String error_screenshot = System.getProperty("user.dir") +
			 * "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
			 * this.takeScreenShot(error_screenshot);
			 */ throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}
	
	
	
	protected abstract ExpectedCondition getPageLoadCondition();
	
	protected abstract void getPageScreenSot();
	

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		wait.until(pageLoadCondition);

	}
	
	
	public void picture() {
		try {
			ExtentManager.captureScreenshot();
			ExtentListeners.testReport.get().info(
					"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		} catch (Exception e) {

		}
	}

	

	public String screenshotName;
	
	public void newPageScreenShot() {
		try {
			ExtentManager.aShot();
			ExtentListeners.testReport.get().info(
					"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
			ExtentListeners.testReport.get().info("<b>" + "<a target=\"_blank\" href='" + screenshotName
					+ "'><img src='" + screenshotName + "height='100 width='100'/></a>" + "</b>");
			// <a target=\"_blank\" href='"+Name+"'><img src='"+Name+"'height='100'
			// width='100'/></a>
		} catch (Exception e) {

		}

	}



	

	public void takeScreenshotByShutterBug(WebElement ele, String imageName) {
		
		Shutterbug.shootElement(DriverManager.getDriver(), ele).withName(imageName).save();
//		log.info("Taken Screen shot of the element");

		try {
			ExtentListeners.testReport.get()
					.info("<b>" + "<font color=" + "white>" + "Screenshot of the section" + "</font>" + "</b>",
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											System.getProperty("user.dir") + "\\screenshots\\" + imageName + ".png")
									.build());

		} catch (Exception e) {

		}

	}

	
	public void aShot() {

		Screenshot fpScreenshot;

		fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(DriverManager.getDriver());

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") +"_"+(int)(Math.random()*1000)+".jpg";
		try {
			ImageIO.write(fpScreenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			ExtentListeners.testReport.get().info(
					"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());



		} catch (Exception e) {

		}

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}
	
	
	
	
}
