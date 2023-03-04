package Base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	
	public static WebDriver driver;
	protected WebDriverWait wait;
	public ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeTest
	public void preRequisite() {
		String reportPath = System.getProperty("user.dir")+"\\reports\\AutomationReport.html";
		reporter = new ExtentHtmlReporter(reportPath);
		reporter.config().setEncoding("utf-8");
		reporter.config().setDocumentTitle("RESS Automation");
		reporter.config().setReportName("Regression Q1 2023 Report");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Platform", "Window 11");
		extent.setSystemInfo("Browser", "Chrome/Edge");
		extent.setSystemInfo("Automation Engineer", "Niteen S.");
	}
	
	@Parameters({"browser"})
	@BeforeMethod
	public void startBrowser(String bname, Method testMethod) {
		logger = extent.createTest(testMethod.getName());
		browserFactory(bname);
		driver.get("https://www.amazon.in/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	
	@AfterMethod
	public void stopBrowser(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		Markup m;
		String logText = "";
		if(result.getStatus() == ITestResult.SUCCESS) {
			logText = "Test Case: " + methodName +  " Passed.";
			m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logText = "Test Case: " + methodName +  " Failed.";
			m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			logText = "Test Case: " + methodName +  " Skipped.";
			m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
		}
		driver.quit();
	}
	
	@AfterTest
	public void postTestMethod() {
		extent.flush();
	}
	
	//This method support cross browser automation
	private void browserFactory(String name) {
		System.out.println("Starting browser: " + name);
		name = name.toLowerCase();
		switch(name) {
		case "chrome"	:  
			ChromeOptions co = new ChromeOptions();
			co.setHeadless(false);
			driver = new ChromeDriver(co);				break;
		case "edge"		: driver = new EdgeDriver();				break;
		case "ie"		: driver = new InternetExplorerDriver();	break;
		case "firefox"	: driver = new FirefoxDriver();				break;
		default			: driver = new ChromeDriver();				break;
		}
		System.out.println("Browser started: " + name);
	}

}
