package test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day_6_IFrames_Handling {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://ksrtc.in");

	}
}
