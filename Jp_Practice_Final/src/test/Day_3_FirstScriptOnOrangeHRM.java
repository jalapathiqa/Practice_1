package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day_3_FirstScriptOnOrangeHRM {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		boolean display_status = userName.isDisplayed();
		boolean enable_status = userName.isEnabled();

		if (display_status && enable_status) {
			userName.sendKeys("Admin");

		}

		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		String urlAfterLogin = driver.getCurrentUrl();
		boolean status = urlAfterLogin.contains("dashboard");

		if (status) {
			System.out.println("login success");
		} else {
			System.out.println("login failed");
		}

		if (driver.getCurrentUrl().contains("dashboard")) {
			System.out.println("2nd - login success");
		}
	}
}
