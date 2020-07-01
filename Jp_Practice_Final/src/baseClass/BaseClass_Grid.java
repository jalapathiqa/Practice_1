package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import excelUtility.Read_Excel;

public class BaseClass_Grid {

	public static Properties configProp;
	public static String appBrowser, appUrl, userName, passWord;
	public static ExtentReports extent;
	public static ExtentTest test;
	private ThreadLocal<RemoteWebDriver> threadedDriver = null;
	private RemoteWebDriver driver = null;
	public ThreadLocal<String> testCaseName;
	public ThreadLocal<Integer> currentRowNumber;
	public ThreadLocal<ArrayList<Integer>> currentRowList;

	public String filelocation = System.getProperty("user.dir") + "/TestData/DataDrivenExcel.xlsx";
	public String sheetName = "TestSheet";
	public Read_Excel readExcelUtil = new Read_Excel(filelocation);

	@BeforeSuite
	public void runBeforeEverything() throws Exception {

		loadPropertiesFiles();

		try {
			appBrowser = configProp.getProperty("browser");
			appUrl = configProp.getProperty("url");
			userName = configProp.getProperty("uName");
			passWord = configProp.getProperty("pWord");

		} catch (Exception e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();
		extent.attachReporter(new ExtentHtmlReporter("./Reports/" + "ExtentReports" + '-' + timeStamp() + ".html"));
	}

	@BeforeClass
	public void initializeBrowser() throws MalformedURLException {

		threadedDriver = new ThreadLocal<RemoteWebDriver>();
		testCaseName = new ThreadLocal<String>();
		currentRowNumber = new ThreadLocal<Integer>();
		currentRowList = new ThreadLocal<ArrayList<Integer>>();
		setTestCaseName(this.getClass().getSimpleName());

		if (appBrowser.equalsIgnoreCase("chrome")) {

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			threadedDriver.set(driver);

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(appUrl);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		} else if (appBrowser.equalsIgnoreCase("ie")) {

			InternetExplorerOptions options = new InternetExplorerOptions();
			options.introduceFlakinessByIgnoringSecurityDomains();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
			threadedDriver.set(driver);

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(appUrl);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		}

	}

	@BeforeMethod
	public void rubBeforeTest(Method method) {
		test = extent.createTest(method.getName());
	}

	@AfterMethod
	public void runAfterTest(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Passed");

			TakesScreenshot ts = driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(
					"./ScreenShots_Pass/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			try {
				FileHandler.copy(src, dest);
				test.addScreenCaptureFromPath(
						"./ScreenShots_Pass/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			} catch (IOException e) {
				System.out.println("could not write screenshot " + e.getMessage());
			}
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Failed");

			TakesScreenshot ts = driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(
					"./ScreenShots_Fail/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			try {
				FileHandler.copy(src, dest);
				test.addScreenCaptureFromPath(
						"./ScreenShots_Fail/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			} catch (IOException e) {
				System.out.println("could not write screenshot " + e.getMessage());
			}
		} else {
			test.log(Status.SKIP, "Skipped");

		}

	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

	@AfterSuite
	public void tearDown() {

		extent.flush();
	}

	public void loadPropertiesFiles() throws Exception {

		configProp = new Properties();
		File fileConfigProp = new File("C:\\config.properties");
		FileInputStream fileConfig = new FileInputStream(fileConfigProp);
		configProp.load(fileConfig);

	}

	public RemoteWebDriver getDriver() {
		return this.driver = threadedDriver.get();
	}

	public String timeStamp() {
		return new SimpleDateFormat("MM/dd/yyyy HH.mm.ss").format(new Date());
	}

	public String getTestCaseName() {
		return testCaseName.get();
	}

	public void setTestCaseName(String testCaseClassName) {
		this.testCaseName.set(testCaseClassName);
	}

	public ArrayList<Integer> getCurrentRowList() {
		return currentRowList.get();
	}

	public void setCurrentRowList(ArrayList<Integer> value) {
		this.currentRowList.set(value);
	}

	public int getCurrentRowNumber() {
		return currentRowNumber.get();
	}

	public void setCurrentRowNumber(int currentRow) {
		this.currentRowNumber.set(currentRow);
	}
}
