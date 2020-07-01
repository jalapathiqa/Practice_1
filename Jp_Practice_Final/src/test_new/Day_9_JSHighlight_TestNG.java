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

public class Day_9_JSHighlight_TestNG {

	public void javaScriptHighlightElement() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://facebook.com/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		JavascriptExecutor js = driver;
		WebElement ele = driver.findElement(By.name("firstname"));
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: solid 2px red');", ele);

		ele.sendKeys("jp");

		js.executeScript("arguments[0].setAttribute('style',' border: solid 2px white');", ele);

	}
}
