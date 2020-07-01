package test_new;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.loginToHRM_PagePbjects;
import baseClass.BaseClass;

public class loginToHRM extends BaseClass {

	String testCaseName = "HRMLogin";

	@DataProvider(name = "HRMLogin")
	public Object[][] getData() {
		return readExcelUtil.retrieveTestData(sheetName, testCaseName);
	}

	@Test(dataProvider = "HRMLogin")
	public void loginHRM(String Execute, String name, String word) {

		loginToHRM_PagePbjects hrm = PageFactory.initElements(driver, loginToHRM_PagePbjects.class);
		hrm.loginToHRM(name, word);
	}

}
