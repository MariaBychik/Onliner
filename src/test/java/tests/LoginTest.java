package tests;

import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners({TestListener.class})
public class LoginTest extends BaseTest{

    @Test(priority = 0,description="Registration with valid values")
    @Description("Test Description: Create account with valid values")
    public void validLoginTest(){
        String email = utils.RandomUtils.getEmailString() + "@gmail.com";
        String password = utils.RandomUtils.getPasswordString();
        String confirmPassword = password;

        LoginPage validLogin = new LoginPage(driver);
        validLogin.clickLoginButton()
                .switchToRegisterPage()
                .inputEmail(email)
                .inputPassword(password)
                .confirmPassword(confirmPassword)
                .clickCheckBox()
                .clickContinueButton();
        Assert.assertTrue(validLogin.isUserLoggedIn(), "User is not logged in");
    }
}
