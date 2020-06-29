package com.projectName.companyName.testcases;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.DemoProfject.CompanyName.Pageobjects.AUTHENTICATIONPAGE;
import com.DemoProfject.CompanyName.Pageobjects.HomePage;
import com.DemoProfject.CompanyName.Utilities.*;




public class TC001 extends BaseTest{
	
	
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void loginTest(Hashtable<String,String> data) throws Exception {

		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);
		
		openBrowser(data.get("browser"));	
		HomePage home = new HomePage().open(getTestSiteURL());
		AUTHENTICATIONPAGE ap = home.clickonsign();
		ap.login(data.get("email"),data.get("password"));
		
	}

	@AfterMethod
	public void tearDown() {
		
		
		
		quit();
		
	}

}
