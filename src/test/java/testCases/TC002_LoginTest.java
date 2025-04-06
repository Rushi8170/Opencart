package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void verify_login() {
		logger.info("-----Starting TC002_LoginTest-----");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); //Home Page login

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("email"));
			lp.setPassword(prop.getProperty("password"));
			lp.clickLogin(); //Login Page login

			MyAccountPage maccp = new MyAccountPage(driver);
			boolean targetPage = maccp.isMyAccountPageExists();
			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("-----Finish TC002_LoginTest-----");
	}
}
