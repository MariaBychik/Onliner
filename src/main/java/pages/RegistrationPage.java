package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class RegistrationPage {
    public WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Вход')]")
    WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Зарегистрироваться на Onlíner')]")
    WebElement registrationButton;

    @FindBy(xpath = "//span[@id='email']")
    WebElement copyMail;

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

    @FindBy(xpath = "//span[@class='glyphicon glyphicon-share-alt zavriOkno zrcadli']")
    WebElement backButton;

    @FindBy(xpath = "(//tr[@class='hidden-xs hidden-sm klikaciRadek newMail'])[1]")
    WebElement switchToConfirm;

    @FindBy(xpath = "//iframe[@id='iframeMail']")
    WebElement iframe;

    @FindBy(xpath = "(//td[@align='center'])[4]")
    WebElement confirmButton;

    @FindBy(xpath = "//a[@class='b-top-profile__preview js-toggle-bar']")
    WebElement accountMenuButton;

    @FindBy(xpath = "//a[@class='b-top-profile__link b-top-profile__link_secondary']")
    WebElement logOutButton;


    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Click Login button")
    public RegistrationPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Switch to register page")
    public RegistrationPage switchToRegisterPage() {
        registrationButton.click();
        return this;
    }

    @Step("Input email")
    public RegistrationPage inputEmail() {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("window.open(\"https://www.fakemail.net\");");
        ArrayList<String> tabs_windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(1));
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(copyMail));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        String mail= driver.findElement(By.xpath("//span[@id='email']")).getText();
        driver.switchTo().window(tabs_windows.get(0));
        emailField.sendKeys(mail);
        return this;
    }

    @Step("Input password")
    public RegistrationPage inputPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Confirm password")
    public RegistrationPage confirmPassword(String confirmPassword){
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    @Step("Click check-box")
    public RegistrationPage clickCheckBox(){
        checkBox.click();
        return this;
    }

    @Step("Complete registration")
    public RegistrationPage clickContinueButton() {
        enterButton.click();
        ArrayList<String> tabs_windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(1));
        switchToConfirm.click();
        backButton.click();
        switchToConfirm.click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(iframe));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", iframe);
        driver.switchTo().frame(iframe);
        confirmButton.click();
        return this;
    }

    @Step("Verify that user is registered")
    public boolean isUserLoggedIn(){
        ArrayList<String> tabs_windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(2));
        WebElement account = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.xpath("//div[@class='profile-header__details-item']")));
        return account.getText().contains("ID");
    }

    @Step()
    public RegistrationPage logOutOfAccount(){
        accountMenuButton.click();
        logOutButton.click();
        return this;
    }
}
