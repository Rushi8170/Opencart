package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verify_account_registration() {

		logger.info("***Starting TC001_AccountRegistrationTest***");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on Registration link");
			hp.clickRegister();

			AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details...");
			registrationPage.setFirstName(randomString().toUpperCase());
			registrationPage.setLastName(randomString().toUpperCase());
			registrationPage.setEmail(randomString() + "j@gmail.com");
			registrationPage.setTelephone(randomNumber());

			String password = randomAlphaNumeric();
			registrationPage.setPassword(password);
			registrationPage.setConfirmPassword(password);

			registrationPage.setPrivacyPolicy();
			registrationPage.clickContinue();

			logger.info("Validating expected message...");
			String cnfmsg = registrationPage.getConfirmationMsg();
			Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test failed...");
			logger.debug("Debug test");
			Assert.fail();
		}
		logger.info("***Finished TC001_AccountRegistrationTest***");
	}
}
