package com.DemoProfject.CompanyName.ExtentListeners;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.DemoProfject.CompanyName.Utilities.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);


		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		

		return extent;

	}

	public static String screenshotPath;
	public static String screenshotName;


	
	
	public static void captureScreenshot() {

		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();

		screenshotName = d.toString().replace(":", "_").replace(" ", "_") +(int) (Math.random()*1000)+ ".jpg";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void captureNewPageScreenshot(String ID) {

		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = ID + d.toString().replace(":", "_").replace(" ", "_")+(int) (Math.random()*1000) + ".jpg";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void aShot() {		
		Screenshot fpScreenshot;		
		WebElement element = DriverManager.getDriver().findElement(By.xpath("//*[@id='header']"));
		JavascriptExecutor js1 = (JavascriptExecutor) DriverManager.getDriver();
		js1.executeScript("arguments[0].setAttribute('class','');", element);
		
		
		try {
			fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(DriverManager.getDriver());
		} catch (Exception e) {
			fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(DriverManager.getDriver());
		}
		
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") +(int) (Math.random()*1000) +".jpg";

		try {
			ImageIO.write(fpScreenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js1.executeScript("arguments[0].setAttribute('class','navbar-fixed-top');", element);


		 

	}

}