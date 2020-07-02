package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Day_9_ActionsClass {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");

//		Actions act = new Actions(driver);
//		act.moveToElement(driver.findElement(By.xpath("//button[text()='Automation Tools']")))
//				.moveToElement(driver.findElement(By.xpath("//a[text()='Appium']"))).click().build().perform();

		List<WebElement> list1 = driver.findElements(By.xpath("//button[text()='Automation Tools']"));

		for (WebElement ele : list1) {
			System.out.println(ele.getText());
		}
		driver.quit();

	}
}
