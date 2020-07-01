package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.SessionId;

public class Day_2_StartBrowsers {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Rishi/Desktop/SeleniumLibrary/chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://eenadu.net");
		System.out.println("+++++++++++ ************************************************* +++++++++++++++++++");
		String url = driver.getCurrentUrl();
		System.out.println("url " + url);
		System.out.println("+++++++++++ ************************************************* +++++++++++++++++++");

		String title = driver.getTitle();
		System.out.println("Title " + title);
		System.out.println("+++++++++++ ************************************************* +++++++++++++++++++");
		String source = driver.getPageSource();
		// System.out.println("source code " + source);
		System.out.println("+++++++++++ ************************************************* +++++++++++++++++++");

		SessionId sessionId = driver.getSessionId();
		System.out.println("sessionId " + sessionId);
		System.out.println("+++++++++++ ************************************************* +++++++++++++++++++");

		driver.quit();

		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * "C:\\Users\\Rishi\\Desktop\\SeleniumLibrary\\geckodriver.exe");
		 * 
		 * FirefoxDriver driver1 = new FirefoxDriver();
		 * driver1.get("https://eenadu.net"); driver1.quit();
		 * 
		 * System.setProperty("webdriver.ie.driver",
		 * "C:\\Users\\Rishi\\Desktop\\SeleniumLibrary\\IEDriverServer.exe");
		 * 
		 * InternetExplorerDriver driver2 = new InternetExplorerDriver();
		 * driver2.get("https://eenadu.net"); driver2.quit();
		 */

	}

}
