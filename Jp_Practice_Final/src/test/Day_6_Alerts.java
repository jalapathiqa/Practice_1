package test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day_6_Alerts {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://ksrtc.in");
		driver.findElement(By.xpath("//*[contains(text(),'Search for Bus')]")).click();

		Alert alt = driver.switchTo().alert();
		System.out.println("alert name: " + alt.getText());
		alt.accept();

		driver.findElement(By.name("fromPlaceName")).sendKeys("BENGALURU");
		Thread.sleep(2000);

		driver.quit();
	}
}
