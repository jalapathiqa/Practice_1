package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.org.apache.bcel.internal.generic.Select;

public class Day_4_DropdownSelection {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.facebook.com");

		WebElement month_dropdown = driver.findElement(By.id("month"));
		org.openqa.selenium.support.ui.Select month = new org.openqa.selenium.support.ui.Select(month_dropdown);

		boolean status = month.isMultiple();
		System.out.println(status);
		// month.selectByVisibleText("Dec");
		System.out.println(month.getFirstSelectedOption().getText());

		List<WebElement> month_list = month.getOptions();

		for (WebElement month_count : month_list) {
			System.out.println("month_count: " + month_count.getText());
		}

		List<WebElement> year_dropdown = driver.findElements(By.id("year"));

		int count_years = year_dropdown.size();
		System.out.println("count_years: " + count_years);

		for (WebElement yrs : year_dropdown) {

			System.out.println(yrs.getText());
		}

		month.selectByVisibleText("Apr");
		driver.quit();

	}
}
