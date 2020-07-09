package com.DemoProfject.CompanyName.Pageobjects;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.DemoProfject.CompanyName.Utilities.DriverManager;

public class w3schoolsPage extends BasePage {

	@FindBy(xpath = "//button[contains(text(),'Try')]")
	WebElement tryIt;

	@FindBy(xpath = "//button[contains(text(),'Open Windows')]")
	WebElement openWindows;

	@FindBy(xpath = "//*[@id='iframewrapper']")
	WebElement iframewrapper;

	@FindBy(xpath = "//*[@name='iframeResult']")
	WebElement iframe;

	@FindBy(xpath = "//*[@name='filename']")
	WebElement choosebutton;

	public w3schoolsPage open(String url) {

		driver.get(url);

		return (w3schoolsPage) openPage(w3schoolsPage.class);

	}

	public void alert() throws InterruptedException {
		System.out.println(driver.findElements(By.xpath("//iframe")).size());
		switchToFrame(1);

		click(tryIt, "tryIt");
		System.out.println(getAlertText());
		Thread.sleep(5000);
		acceptAlert();
	}

	public void uploadfile() throws InterruptedException, Exception {

		switchToFrame(1);
		click(choosebutton, "choose button");
//		type(choosebutton, "C:\\Users\\Rekha\\Desktop\\Choice\\internet speed.JPG", "Image");
		Thread.sleep(3000);
		
		Runtime.getRuntime().exec("D:\\workspace\\DemoProject\\src\\test\\resources\\testData\\uploadtextfile.exe");

		
		
		
		
		Thread.sleep(5000);

	}

	public void handlingWindows() throws Exception {

		String parent = DriverManager.getDriver().getWindowHandle();

		switchToFrame(iframe);

		click(openWindows, "open Windows");
		Set<String> s1 = DriverManager.getDriver().getWindowHandles();

		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {
			String Child_window = I1.next();

			if (!parent.equals(Child_window)) {

				System.out.println(Child_window);
				DriverManager.getDriver().switchTo().window(Child_window);
				if (DriverManager.getDriver().getTitle().equals("Google")) {

					// DriverManager.getDriver().findElement(By.name("q")).sendKeys("Selenoum");
					robot.keyPress(KeyEvent.VK_S);
					robot.keyPress(KeyEvent.VK_E);
					robot.keyPress(KeyEvent.VK_L);
					robot.keyPress(KeyEvent.VK_E);
					robot.keyPress(KeyEvent.VK_N);
					robot.keyPress(KeyEvent.VK_I);
					robot.keyPress(KeyEvent.VK_U);
					robot.keyPress(KeyEvent.VK_M);

					Thread.sleep(5000);
				}

				DriverManager.getDriver().close();
			}
		}
		DriverManager.getDriver().switchTo().window(parent);

		Thread.sleep(5000);

	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(iframewrapper);

	}

	@Override
	protected void getPageScreenSot() {
		// aShot();
		picture();

	}

}
