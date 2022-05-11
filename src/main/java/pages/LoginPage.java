package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Вход')]")
    WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Зарегистрироваться на Onlíner')]")
    WebElement registrationButton;

    @FindBy(xpath = "//input[@placeholder='Ваш e-mail']")
    WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Придумайте пароль']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@placeholder='Повторите пароль']")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//span[@class='i-checkbox__faux']")
    WebElement checkBox;

    @FindBy(xpath = "//div[@class='auth-form__control auth-form__control_condensed-default']")
    WebElement enterButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Click Login button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Switch to register page")
    public LoginPage switchToRegisterPage() {
        registrationButton.click();
        return this;
    }

    @Step("Input email")
    public LoginPage inputEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    @Step("Input password")
    public LoginPage inputPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Confirm password")
    public LoginPage confirmPassword(String confirmPassword){
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    @Step("Click check-box")
    public LoginPage clickCheckBox(){
        checkBox.click();
        return this;
    }

    @Step("Complete registration")
    public LoginPage clickContinueButton(){
        enterButton.click();
        return this;
    }

    @Step("Verify that user is logged in")
    public boolean isUserLoggedIn(){
        WebElement account = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.xpath("//div[@class='auth-form__title auth-form__title_big auth-form__title_condensed-default']")));
        return account.getText().contains("Подтвердите");
    }
}
