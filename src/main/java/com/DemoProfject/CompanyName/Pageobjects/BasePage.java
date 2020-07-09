package com.DemoProfject.CompanyName.Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DemoProfject.CompanyName.ExtentListeners.ExtentListeners;
import com.DemoProfject.CompanyName.ExtentListeners.ExtentManager;
import com.DemoProfject.CompanyName.Utilities.DriverCapabilities;
import com.DemoProfject.CompanyName.Utilities.DriverManager;
import com.DemoProfject.CompanyName.Utilities.JavaScript;
import com.DemoProfject.CompanyName.Utilities.RobotClass;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public abstract class BasePage<T> {
	
	
	
protected WebDriver driver;
	
	
	private long LOAD_TIMEOUT = 30;
	private int AJAX_ELEMENT_TIMEOUT = 150;
	public int expTime = 60;
	protected JavascriptExecutor exe;
	protected WebDriverWait wait;
	protected Capabilities caps;
	protected Robot robot;

	public BasePage() {
		this.driver = DriverManager.getDriver();
		this.caps = DriverCapabilities.getCapabilities();
		this.exe = JavaScript.getJavaScriptObject();
//		this.wait = waitHelper.getWebDriverWaitObject();
		this.robot = RobotClass.getRobotClassObject();
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
//		// log.info("Taken Screen shot of the element");

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
	
	
	public void click(WebElement element, String elementName) {

		ExtentListeners.testReport.get().info("Clicking on : " + elementName);
//		// log.info("Clicking on : " + elementName);
		highlightElement(element);
		element.click();
	}

	public void type(WebElement element, String value, String elementName) {
		
//		// log.info("Typing in : " + elementName + " entered the value as : " + value);
		ExtentListeners.testReport.get().info("Typing in : " + elementName + " entered the value as : " + value);
		element.clear();
		highlightElement(element);
		element.sendKeys(value);
	}
	
	
	
	
	
	
	public void selectUsingVisibleText(WebElement element, String visibleText, String elementName) {
//		log.info("Selecting the +" + elementName + "+ value as : " + visibleText);
		ExtentListeners.testReport.get().info("Selecting the " + elementName + " value as : " + visibleText);
		Select sel = new Select(element);
//		highlightElement(element);
		sel.selectByVisibleText(visibleText);
	}	
	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
//		log.info("selectUsingIndex and index is: "+index);
		select.selectByIndex(index);
	}	
	public void selectUsingValue(WebElement element, String value){
		Select select = new Select(element);
//		log.info("selectUsingValue and value is: "+value);
		select.selectByValue(value);
	}	
	public void deSelectUsingValue(WebElement element, String value){
		Select select = new Select(element);
//		log.info("deSelectUsingValue and value is: "+value);
		select.deselectByValue(value);
	}	
	public void deSelectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
//		log.info("deSelectUsingIndex and index is: "+index);
		select.deselectByIndex(index);
	}	
	public void deSelectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
//		log.info("deselectByVisibleText and visibleText is: "+visibleText);
		select.deselectByVisibleText(visibleText);
	}	
	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement ele: elementList){
//			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}
	
	
	//Actions - 
	
	
	public void moveToElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).perform();
	}
	
	
	
	
	
	// *******************************************JavaScript*********************************//
	
	
		/**
		 *  highlighting Element 
		 * @param element 
		 */
		public void highlightElement(WebElement element) {
			exe.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		}
		/**
		 * This method will scroll till element
		 * @param element
		 */	
		public void scrollToElement(WebElement element) {
//			log.info("scroll to WebElement...");
			exe.executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
			element.getLocation().y);
		}
		/**
		 * Scroll till element and click
		 * @param element
		 */	
		public void scrollToElementAndClick(WebElement element) {
			scrollToElement(element);
			element.click();
//			log.info("element is clicked: " + element.toString());
		}
		/**
		 * This method will execute java script
		 * @param script
		 * @return
		 */	
		public Object executeScript(String script) {
			return exe.executeScript(script);
		}
		/**
		 * This method will execute Java script with multiple arguments
		 * 
		 * @param script
		 * @param args
		 * @return
		 */
		public Object executeScript(String script, Object... args) {
			return exe.executeScript(script, args);
		}
		/**
		 * Type By Java Script
		 * 
		 * @param element
		 */	
		public void TypeByJavaScript(String Value, WebElement element) {
			executeScript("arguments[0].value='" + Value + "';", element);
		}
		/**
		 * getLink
		 * 
		 * @param element
		 */
		public String getLink(WebElement element) {
			String hrefUrl = element.getAttribute("href");
//			log.info("Link is "+ hrefUrl);	
			setAttribute(element, "target", "_self");		
			return hrefUrl;
		}
		/**
		 * Update the Attribute of the element 
		 * @param element,Attribute and value
		 */
		public void setAttribute(WebElement element,String attribute, String value) {
			
//			log.info("Attribute is "+attribute +" value is "+value);
			executeScript("arguments[0].setAttribute('" + attribute + "','" + value + "');", element);
		}	
		/**
		 * Scroll till element view	 * 
		 * @param element
		 */	
		public void scrollIntoView(WebElement element) {
//			log.info("scroll till web " + element);
			executeScript("arguments[0].scrollIntoView()", element);
		}
		/**
		 * Scroll till element view and click
		 * 
		 * @param element
		 */	
		public void scrollIntoViewAndClick(WebElement element) {
			scrollIntoView(element);
			element.click();
//			log.info("element is clicked: " + element.toString());
		}
		/**
		 * This method will scroll down vertically
		 */
		public void scrollDownVertically() {
//			log.info("scrolling down vertically...");
			executeScript("window.scrollTo(0,document.body.scrollHeight)");
		}
		/**
		 * This method will scroll up vertically
		 */
		public void scrollUpVertically() {
//			log.info("scrolling up vertically...");
			executeScript("window.scrollTo(0,-document.body.scrollHeight)");
		}
		/**
		 * This method will scroll till given pixel (e.g=1500)
		 * 
		 * @param pixel
		 */
		public void scrollDownByPixel(int pixel) {
			executeScript("window.scrollBY(0," + pixel + ")");
		}
		public void scrollUpByPixel(int pixel) {
			executeScript("window.scrollBY(0,-" + pixel + ")");
		}
		/**
		 * This method will zoom screen by 100%
		 */
		public void zoomInBy100Percentage() {
			executeScript("document.body.style.zoom='100%'");
		}
		/**
		 * This method will zoom screen by 60%
		 */
		public void zoomInBy60Percentage() {
			executeScript("document.body.style.zoom='40%'");
		}
		/**
		 * This method will click on element
		 * 
		 * @param element
		 */
		public void clickElementByJavaScript(WebElement element) {
			highlightElement(element);
			executeScript("arguments[0].click();", element);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	Alerts	
	
	public Alert getAlert(){
//		// log.info("alert test: "+DriverManager.getDriver().switchTo().alert().getText());
		return DriverManager.getDriver().switchTo().alert();
	}	
	public void acceptAlert(){
		getAlert().accept();
//		// log.info("accept Alert is done...");
	}	
	public void dismissAlert(){
		getAlert().dismiss();
		// log.info("dismiss Alert is done...");
	}	
	public String getAlertText(){
		String text = getAlert().getText();
		// log.info("alert text: "+text);
		return text;
	}	
	public boolean isAlertPresent(){
		try{
			DriverManager.getDriver().switchTo().alert();
			// log.info("alert is present");
			return true;
		}
		catch(NoAlertPresentException e){
			// log.info(e.getCause());
			return false;
		}
	}	
	public void acceptAlertIfPresent(){
		if(isAlertPresent()){
			acceptAlert();
		}
		else{
			// log.info("Alert is not present..");
		}
	}	
	public void dismissAlertIfPresent(){
		if(isAlertPresent()){
			dismissAlert();
		}
		else{
			// log.info("Alert is not present..");
		}
	}	
	public void acceptPrompt(String text){
		if(isAlertPresent()){
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			// log.info("alert text: "+text);
		}
	}
	
	
	
	
//	Iframes
	/**
	 * this method will switchToFrame based on frame index
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex){
		DriverManager.getDriver().switchTo().frame(frameIndex);
//		log.info("switched to :"+ frameIndex+" frame");
	}	
	/**
	 * this method will switchToFrame based on frame name
	 * @param frameName
	 */
	public void switchToFrame(String frameName){
		DriverManager.getDriver().switchTo().frame(frameName);
//		log.info("switched to :"+ frameName+" frame");
	}	
	/**
	 * this method will switchToFrame based on frame WebElement
	 * @param element
	 */
	public void switchToFrame(WebElement element){
		DriverManager.getDriver().switchTo().frame(element);
//		log.info("switched to frame "+element.toString());
	}
	
	
	
	
	
	
}
