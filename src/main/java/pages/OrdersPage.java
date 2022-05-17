package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrdersPage {
    public WebDriver driver;

    @FindBy(xpath = "//span[@class='b-main-navigation__text'][text()='Услуги']")
    WebElement ordersTab;

    @FindBy(xpath = "//a[@class='service-summary__details-item service-summary__details-item_briefcase ng-binding']")
    WebElement orderTitle;

    @FindBy(xpath = "//div[@class='service-form__collapse-body']")
    WebElement sectionTab;

    @FindBy(xpath = "(//a[@class='service-offers__details-item service-offers__details-item_briefcase ng-binding ng-scope'][text()='Комплексный ремонт'])[5]")
    WebElement orderField;

    @FindBy(xpath = "(//div[@class='service-offers__data'])[4]")
    WebElement orderBox;

    @FindBy(xpath = "(//span[@class='project-navigation__sign'])[2]")
    WebElement performersTab;

    @FindBy(xpath = "//div[@class='service-filter__part service-filter__part_2']")
    WebElement serviceField;

    @FindBy(xpath = "(//span[@class='ng-binding ng-scope'][contains (text(), 'Подготовка документов для визы')])[1]")
    WebElement orderPerformersField;

    @FindBy(xpath = "(//div[@class='service-offers__data'])[1]")
    WebElement orderPerformersBox;


    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public OrdersPage returnHomePage() {
        driver.navigate().to("https://www.onliner.by");
        return this;
    }

    @Step("Switch to Услуги tab ")
    public OrdersPage switchToOrdersTab() {
            ordersTab.click();
        return this;
    }

    @Step("Select the orders section for the customer")
    public OrdersPage selectCustomersOrdersSection(String section) {
        String xpath = "//a[@class='service-form__link service-form__link_alter service-form__link_base service-form__link_arrow_bottom ng-binding'][text()= '%s']";
        if (!driver.findElement(By.xpath(String.format(xpath, section))).isSelected()) {
            driver.findElement(By.xpath(String.format(xpath, section))).click();
        }
        return this;
    }

    @Step("Filtering the selected section")
    public OrdersPage filterCustomersOrder(String order) {
        String xpath = "//span[@class='service-form__checkbox-sign ng-binding'][text()='%s']";
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(sectionTab));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        if (!driver.findElement(By.xpath(String.format(xpath, order))).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(xpath, order))));
            driver.findElement(By.xpath(String.format(xpath, order))).click();
        }
        return this;
    }

    @Step("Select the order for the customer")
    public OrdersPage selectCustomersOrder() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(orderField));
            orderBox.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Switch to Исполнители tab")
    public OrdersPage switchToPerformersTab() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(orderTitle));
            performersTab.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Select the orders section for the performers")
    public OrdersPage selectPerformersOrdersSection(String sectionPerformers) {
        String xpath = "//a[@class='service-form__link service-form__link_alter service-form__link_base service-form__link_arrow_bottom ng-binding'][text()= '%s']";
        if (!driver.findElement(By.xpath(String.format(xpath, sectionPerformers))).isSelected()) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(serviceField));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(xpath, sectionPerformers))));
                driver.findElement(By.xpath(String.format(xpath, sectionPerformers))).click();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Step("Filtering the selected section")
    public OrdersPage filterPerformersOrder(String orderPerformers) {
        String xpath = "//span[@class='service-form__checkbox-sign ng-binding'][text()='%s']";
        if (!driver.findElement(By.xpath(String.format(xpath, orderPerformers))).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(String.format(xpath, orderPerformers))));
        }
        return this;
    }

    @Step("Select the order for the performers")
    public OrdersPage selectPerformersOrder() {
        if(orderPerformersField.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderPerformersBox);
            orderPerformersBox.click();
        }
        return this;
    }

    public boolean getCustomerOrderType() {
            WebElement confirm = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver -> driver.findElement(By.xpath("//a[@class='service-summary__details-item service-summary__details-item_briefcase ng-binding'][contains(text(), 'Комплексный ремонт')]")));
            return confirm.getText().contains("Комплексный ремонт");
        }


    public boolean getPerformerOrderType() {
        WebElement confirm = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(driver -> driver.findElement(By.xpath("//div[@class='service-summary__title service-summary__title_middle'][contains(text(), 'Оказываемые услуги')]")));
        return confirm.getText().contains("Оказываемые услуги");
    }
}








