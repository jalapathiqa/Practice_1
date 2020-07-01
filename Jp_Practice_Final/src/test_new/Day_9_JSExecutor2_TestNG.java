package test_new;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

public class Day_9_JSExecutor2_TestNG {

	@Test
	public void javaScriptExecutor() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://makemytrip.com/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement ele = driver.findElement(By.xpath(
				"//div[contains(@class,'fsw_inputBox dates inactiveWidget')]//p[@class='blackText font20 code']"));

		JavascriptExecutor js = driver;
		js.executeScript("arguments[0].click();", ele);

		Thread.sleep(2000);

	}
}
