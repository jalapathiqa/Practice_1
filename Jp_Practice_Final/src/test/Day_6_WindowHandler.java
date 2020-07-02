package test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day_6_WindowHandler {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");

		String parentWindow = driver.getWindowHandle();
		System.out.println("ParentWindow: " + parentWindow);

		System.out.println("wondows size: " + driver.getWindowHandles().size());

		driver.findElement(By.xpath("//img[contains(@alt,'LinkedIn OrangeHRM group')]")).click();

		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("all windows: " + allWindows.size());

		Iterator<String> itr = allWindows.iterator();

		while (itr.hasNext()) {
			String child_window = itr.next();

			if (!child_window.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(child_window);
				System.out.println("title of child window: " + driver.getTitle());
			}
		}

		driver.switchTo().window(parentWindow);
		// driver.switchTo().defaultContent();
	}

}
