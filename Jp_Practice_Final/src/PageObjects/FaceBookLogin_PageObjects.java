package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FaceBookLogin_PageObjects {

	private WebDriver driver;
	
	public FaceBookLogin_PageObjects(WebDriver driver) {
		this.driver = driver;
	}

	// define user name
	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	private WebElement email;

	// define password
	@FindBy(how = How.XPATH, using = "//input[@id='pass']")
	private WebElement passWord;

	// submit button
	@FindBy(how = How.XPATH, using = "//button[@name='login']") 
	private WebElement loginBtn;

	public void loginToFB(String uName, String pWord) {

		email.sendKeys(uName);
		passWord.sendKeys(pWord);
		loginBtn.click();

	}
}
