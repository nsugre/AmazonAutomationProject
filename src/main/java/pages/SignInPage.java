package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	
	public SignInPage(WebDriver wd, WebDriverWait wt){
		driver	= wd;
		wait 	= wt;
	}
	
	WebDriver driver;
	WebDriverWait wait;
	
	private By signinHeaderLoc 	= By.tagName("h1");
	private By emailORmobLoc	= By.id("ap_email");
	private By continueBtnLoc	= By.id("continue");
	private By passwordLoc		= By.id("ap_password");
	private By signInBtnLoc		= By.id("signInSubmit");
	
	//validate Sign Page
	public String validateSignInPage() {
		System.out.println("Executing validate sign in page method.");
		System.out.println("Validating Sign in page title");
		String actTitle = driver.getTitle();
		return actTitle;
	}
	
	public String validateSignInHeader() {
		System.out.println("Executing validate sign in header method.");
		System.out.println("Validating Sign in page header text");
		String actTitle = driver.findElement(signinHeaderLoc).getText();
		return actTitle;
	}
	
	//perform sign in operation
	public void performSignIn(String uValue, String pValue) {
		driver.findElement(emailORmobLoc).sendKeys(uValue);
		wait.until(ExpectedConditions.presenceOfElementLocated(continueBtnLoc)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoc)).sendKeys(pValue);
		wait.until(ExpectedConditions.elementToBeClickable(signInBtnLoc)).click();
	}

}
