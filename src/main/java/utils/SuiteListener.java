package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import Base.BaseClass;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
	
	public void onTestFailure(ITestResult result) {
		
		String fpath = System.getProperty("user.dir") + "\\screenshots\\" + result.getName() + ".png";
		
		TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest	= new File(fpath);
		
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyser.class);
	}
	
}
