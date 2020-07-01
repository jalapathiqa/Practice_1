package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Day_7_dropDowns {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/test/newtours/register.php");

//		List<WebElement> allCountries = driver.findElements(By.xpath("//select[@name='country']"));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		// Thread.sleep(2000);
//		for (WebElement ele : allCountries) {
//			// System.out.println(ele.getText());
//			// driver.findElement(By.xpath("//select[@name='country']")).click();
//
//			if (ele.getText().equalsIgnoreCase("ANGOLA")) {
//				ele.click();
//				break;
//			}
//		}

		WebElement list1 = driver.findElement(By.xpath("//select[@name='country']"));
		Select sel = new Select(list1);

		List<WebElement> list2 = sel.getOptions();

		for (WebElement ele : list2) {
			System.out.println(ele.getText());

			if (ele.getText().equalsIgnoreCase("INDIA")) {
				ele.click();
				break;
			}
		}

	}
}
