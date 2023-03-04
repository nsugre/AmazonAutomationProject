package testAutomation2023;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import pages.SignInPage;
import pages.WelcomePage;
import utils.Reader;

public class AmazonETETest extends BaseClass {

	
	@Test(dataProvider = "cred", dataProviderClass = Reader.class)
	public void positiveTest(String uname, String pwd) throws InterruptedException {

		boolean isOnValidPage = false;
		boolean isValidHeader = false;

		WelcomePage wc = new WelcomePage(driver,wait);

		isOnValidPage = wc.validatePageTitle().startsWith("Online Shopping site in India:");
		Assert.assertEquals(isOnValidPage, true);

		SignInPage sp = wc.navigateToSignInPage();

		isOnValidPage = sp.validateSignInPage().equalsIgnoreCase("Amazon Sign In");
		Assert.assertEquals(isOnValidPage, true);

		isValidHeader = sp.validateSignInHeader().contains("Sign in");
		Assert.assertEquals(isValidHeader, true);

		sp.performSignIn(uname,pwd);
		
		System.out.println("Test is completed");
	}
	
	
	@Test(enabled = false, dataProvider = "cred", dataProviderClass = Reader.class)
	public void negativeTest(String uname, String pwd) throws InterruptedException {

		boolean isOnValidPage = false;
		boolean isValidHeader = false;

		WelcomePage wc = new WelcomePage(driver,wait);

		isOnValidPage = wc.validatePageTitle().startsWith("Online Shopping site in India:");
		Assert.assertEquals(isOnValidPage, true);

		SignInPage sp = wc.navigateToSignInPage();

		isOnValidPage = sp.validateSignInPage().equalsIgnoreCase("Amazon Sign In");
		Assert.assertEquals(isOnValidPage, true);

		isValidHeader = sp.validateSignInHeader().contains("Sign inq");
		Assert.assertEquals(isValidHeader, true);

		sp.performSignIn(uname, pwd);
		
		System.out.println("Test is completed");
	}

}
