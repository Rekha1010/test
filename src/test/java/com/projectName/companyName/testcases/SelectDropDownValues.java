package com.projectName.companyName.testcases;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import com.DemoProfject.CompanyName.Pageobjects.*;
import com.DemoProfject.CompanyName.Utilities.*;




public class SelectDropDownValues extends BaseTest{
	
	
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void loginTest(Hashtable<String,String> data) throws Exception {

		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);
		
		openBrowser(data.get("browser"));	
		FacebookPage home = new FacebookPage().open(getTestSiteURL());
		home.selectmonth();
		
	}

	@AfterMethod
	public void tearDown() {
		
		
		
		quit();
		
	}

}
