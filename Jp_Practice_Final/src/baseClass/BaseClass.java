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

import excelUtility.Read_Excel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties configProp;
	public static String appBrowser, appUrl, userName, passWord, httpURL, waitsURL,FaceBookUrl;
		
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter sparkReporter;
	public File ereports= new File("/Jp_Practice_Final/NewReports" + "ExtentReports" + '-' + timeStamp() + ".html");
	
	public static WebDriver driver;
	public String filelocation = System.getProperty("user.dir") + "/TestData/DataDrivenExcel.xlsx";
	public String sheetName = "TestSheet";
	public Read_Excel readExcelUtil = new Read_Excel(filelocation);
	

	@BeforeSuite 
	public void runBeforeEverything() throws Exception {
		loadPropertiesFiles();

		try {
			appBrowser = configProp.getProperty("browser");
			appUrl = configProp.getProperty("urlOrangeHRM");
			userName = configProp.getProperty("username");
			passWord = configProp.getProperty("password");
			httpURL = configProp.getProperty("restAPRIurl");
			waitsURL = configProp.getProperty("waits");
			FaceBookUrl = configProp.getProperty("fbUrl");
			
			System.out.println(appBrowser);
			System.out.println(FaceBookUrl);


		} catch (Exception e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();		
		sparkReporter = new ExtentSparkReporter(ereports);
		extent.attachReporter(sparkReporter);
		
		
		extent.setSystemInfo("user", "Jp");
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
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
		} else if (appBrowser.equalsIgnoreCase("ie")) {

			WebDriverManager.edgedriver().setup();
					
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
//			driver.get(appUrl);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
		}
		driver.get(FaceBookUrl);


	}

	@BeforeMethod
	public void runBeforeTest(Method method, ITestResult result) {
		test = extent.createTest(method.getName());
//				.addScreenCaptureFromPath(result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
		
		// test.log(Status.PASS, "Test Case Passed is "+method.getName());
	}

	@AfterMethod
	public void runAfterTest(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case Passed is " + result.getName());
			
//			String path = captureScreenShot_Pass("./ScreenShots_Pass/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			String path = captureScreenShot_Pass("./ScreenShots_Pass/" + "ExtentReports" + "-" + timeStamp() + ".jpg");

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("./ScreenShots_Pass/"+ result.getMethod().getMethodName() + "-" + timeStamp() + ".png");
			try {
				FileUtils.copyFile(src, dest);
				
			} catch (IOException e) {
				System.out.println("could not write screenshot " + e.getMessage());
			}
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Failed: " + result.getName());
			test.log(Status.FAIL, "Failed: " + result.getThrowable());
			
//			String path = captureScreenShot_Fail("./ScreenShots_Fail/" + result.getMethod().getMethodName() + '-' + timeStamp() + ".png");
			String path = captureScreenShot_Pass("./ScreenShots_Fail/" + "ExtentReports" + "-" + timeStamp() + ".png");


			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("./ScreenShots_Fail/"+' ' + result.getMethod().getMethodName() + "-" + timeStamp() + ".png");
			try {
				FileUtils.copyFile(src, dest);
			} catch (IOException e) {
				System.out.println("could not write screenshot " + e.getMessage());
			}
		} else {
			test.log(Status.SKIP, "Skipped: " + result.getName());

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
