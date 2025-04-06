package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException {

		logger.info("-----Starting TC003_LoginDDT-----");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); // Home Page login

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin(); // Login Page login

			MyAccountPage maccp = new MyAccountPage(driver);
			boolean targetPage = maccp.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					maccp.clickLogout();
					Assert.assertTrue(true);
				} else
					Assert.assertTrue(false);
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					maccp.clickLogout();
					Assert.assertTrue(false);
				} else
					Assert.assertTrue(true);
			}
		} catch (Exception e) {
			Assert.fail();
		}

		Thread.sleep(3000);
		logger.info("-----Finish TC003_LoginDDT-----");
	}
}
