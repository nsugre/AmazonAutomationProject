package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseClass;

public class WelcomePage {
	
	public WelcomePage(WebDriver wd, WebDriverWait wt){
		driver	= wd;
		wait 	= wt;
	}
	
	private WebDriver driver;
	private WebDriverWait wait;
	private By singInLoc 	= By.id("nav-link-accountList");
	private By searchBoxLoc = By.id("twotabsearchtextbox");

	// validating that I am on correct page
	public String validatePageTitle() {
		BaseClass.logger.info("Executing validate page title method.");
		BaseClass.logger.info("Validating welcome page title.");
		String actTitle = driver.getTitle();
		return actTitle;
	}
	
	//Navigate to SignIn page
	public SignInPage navigateToSignInPage() {
		BaseClass.logger.info("Executing navigate to Sign in page method.");
		BaseClass.logger.info("Executing navigate to Sign in page method.");
		driver.findElement(singInLoc).click();
		BaseClass.logger.info("succesfully clicked on Sign in link.");
		return new SignInPage(driver,wait);
	}
	
	

}
