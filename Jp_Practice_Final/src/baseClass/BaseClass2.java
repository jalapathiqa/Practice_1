package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import excelUtility.Read_Excel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass2 {

	public static Properties configProp;
	public static String appBrowser, appUrl, userName, passWord, httpURL;
		
	public static ExtentReports extent;
//	public static ExtentTest test;	
	public static ExtentSparkReporter sparkReporter;
	
	public static WebDriver driver;
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
			httpURL = configProp.getProperty("restAPRIurl");
			
			System.out.println(appBrowser);
			System.out.println(appUrl);


		} catch (Exception e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();	
		File ereports = new File(System.getProperty("user.dir") +"/NewReports/" + "extentReports.html"+' '+timeStamp());
		sparkReporter = new ExtentSparkReporter(ereports);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("ExtentReports");
		sparkReporter.config().setDocumentTitle("ExtentReports");
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		sparkReporter.config().setCss(".badge-primary{background-color:#da0b2b}");
		
		extent.attachReporter(sparkReporter);
		
	}

	@BeforeClass
	public void initializeBrowser() {

		if (appBrowser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
			
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
			driver.get(appUrl);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
		} else if (appBrowser.equalsIgnoreCase("ie")) {

			WebDriverManager.edgedriver().setup();
					
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
			driver.get(appUrl);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
		}

	}

	@BeforeMethod
	public void runBeforeTest(Method method, ITestResult result) {
//		test = extent.createTest(method.getName());
		String path = captureScreenShot_Pass(result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
		extent.createTest(result.getMethod().getMethodName())
				.addScreenCaptureFromPath(path);
		
		// test.log(Status.PASS, "Test Case Passed is "+method.getName());
	}

	@AfterMethod
	public void runAfterTest(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
//			test.log(Status.PASS, "Test Case Passed is " + result.getName());
			
			
//			String path = captureScreenShot_Pass("./ScreenShots_Pass/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			String path = captureScreenShot_Pass(result.getMethod().getMethodName() + '-' + timeStamp() + ".png");

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir") +"/ScreenShots_Pass/"+' ' + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			
			try {
				FileHandler.copy(src, dest);
//				test.addScreenCaptureFromPath(path);
				
			} catch (IOException e) {
				System.out.println("failed to write screenshot " + e.getMessage());
			}
		} else if (result.getStatus() == ITestResult.FAILURE) {
//			test.log(Status.FAIL, "Failed: " + result.getName());
//			test.log(Status.FAIL, "Failed: " + result.getThrowable());
			
//			String path = captureScreenShot_Fail("./ScreenShots_Fail/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			String path = captureScreenShot_Fail(result.getMethod().getMethodName() + '-' + timeStamp() + ".png");


			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir") +
					"/ScreenShots_Fail/"+' ' + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			try {
				FileHandler.copy(src, dest);
//				test.addScreenCaptureFromPath(path);
			} catch (IOException e) {
				System.out.println("failed to write screenshot " + e.getMessage());
			}
		} else {
//			test.log(Status.SKIP, "Skipped: " + result.getName());

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
		File fileConfigProp = new File("C:\\Users\\kalag\\config.properties.txt");
		FileInputStream fileConfig = new FileInputStream(fileConfigProp);
		configProp.load(fileConfig);

	}

	public String timeStamp() {
		return new SimpleDateFormat("MM/dd/yyyy HH.mm.ss").format(new Date());
	}
	
	public static String captureScreenShot_Fail(String fileName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShots_Fail/"+ fileName);
		
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		
	return destFile.getAbsolutePath();
	}
	
	public static String captureScreenShot_Pass(String fileName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShots_Pass/"+ fileName);
		
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		
	return destFile.getAbsolutePath();
	}


}
