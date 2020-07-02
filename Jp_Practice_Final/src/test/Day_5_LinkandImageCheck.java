package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day_5_LinkandImageCheck {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://orangehrm.com/");

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		System.out.println("total links: " + allLinks.size());

		for (WebElement links : allLinks) {
			System.out.println(links.getText());

		}

//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-2']//a[contains(text(),'Blog')]")).click();

		List<WebElement> allImgs = driver.findElements(By.tagName("img"));
		System.out.println("total links: " + allImgs.size());

		for (WebElement Images : allImgs) {
			System.out.println("total images: " + Images.getAttribute("src"));
		}

		driver.quit();
	}

}
