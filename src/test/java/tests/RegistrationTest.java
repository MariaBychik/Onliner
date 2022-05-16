package tests;

import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegistrationPage;

@Listeners({TestListener.class})
public class RegistrationTest extends BaseTest{

    @Test(priority = 0,description="Registration with valid values")
    @Description("Test Description: Create account with valid values")
    public void validLoginTest(){
        String password = utils.RandomUtils.getPasswordString();

        RegistrationPage validLogin = new RegistrationPage(driver);
        SoftAssert Assert = new SoftAssert();
        validLogin.clickLoginButton()
                .switchToRegisterPage()
                .inputEmail()
                .inputPassword(password)
                .confirmPassword(password)
                .clickCheckBox()
                .clickContinueButton();
        Assert.assertTrue(validLogin.isUserLoggedIn(), "User is not logged in");
        validLogin.logOutOfAccount();
        Assert.assertAll();
    }
}
