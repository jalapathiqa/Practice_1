package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day_7_Calendars {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");

		driver.findElement(By.xpath("//input[@id='datepicker']")).click();

		List<WebElement> allDates = driver
				.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//td//a"));

		for (WebElement dates : allDates) {
			// System.out.println(dates.getText());
			System.out.println(dates.getAttribute("innerHTML"));

			if (dates.getText().equalsIgnoreCase("23")) {
				dates.click();
				break;
			}
		}

		DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		String sdf = new SimpleDateFormat().format(cal.getTime());
		int day = cal.get(Calendar.DAY_OF_MONTH);

		System.out.println(day);
		System.out.println("Date: " + sdf);

	}
}
