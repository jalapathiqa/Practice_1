package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class loginToHRM_PagePbjects {

	WebDriver driver;

	// define user name
	@FindBy(how = How.ID, using = "txtUsername")
	private WebElement userName;

	// define password
	@FindBy(how = How.ID, using = "txtPassword")
	private WebElement passWord;

	// submit button
	@FindBy(how = How.ID, using = "btnLogin")
	private WebElement loginBtn;

	public void loginToHRM(String uName, String pWord) {

		userName.sendKeys(uName);
		passWord.sendKeys(pWord);
		loginBtn.click();

	}
}
