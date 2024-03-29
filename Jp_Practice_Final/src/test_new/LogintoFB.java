package test_new;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.FaceBookLogin_PageObjects;
import baseClass.BaseClass;

public class LogintoFB extends BaseClass {
	String testCaseName = "FBLogin";

//	FaceBookLogin_PageObjects fb;

	@DataProvider(name = "FBLogin")
	public Object[][] getData() {
		return readExcelUtil.retrieveTestData(sheetName, testCaseName);
	}

	@Test(dataProvider = "FBLogin")
	public void LoginFaceBook(String Execute, String email, String pWord) throws InterruptedException {

		FaceBookLogin_PageObjects fb = PageFactory.initElements(driver, FaceBookLogin_PageObjects.class);
		fb = new FaceBookLogin_PageObjects(driver);
		fb.loginToFB(email, pWord);
		Thread.sleep(2000);
	}

}
