package pages;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class SearchPage {
    public WebDriver driver;

    @FindBy(xpath = "//span[@class='b-main-navigation__text'][contains(text(), 'Каталог')]")
    WebElement catalogButton;

    @FindBy(xpath = "//input[@class='fast-search__input']")
    WebElement searchField;

    @FindBy(xpath = "//body/div[@id='fast-search-modal']/div[1]/div[1]/iframe[1]")
    WebElement iframe;

    @FindBy(xpath = "(//div[@class='product__offers'])[1]")
    WebElement searchResult;

    @FindBy(xpath = "//div[@class='offers-list__target']")
    WebElement localityField;

    @FindBy(xpath = "//div[@class='offers-form__description offers-form__description_alter offers-form__description_base-alter offers-form__description_condensed' and contains(text(),'Ваш населенный пункт —')]")
    WebElement bannerAccept;

    @FindBy(xpath = "(//span[@class='button-style button-style_another button-style_base offers-form__button'])[1]")
    WebElement acceptButton;

    @FindBy(xpath = "(//*[contains(text(),'В корзину')])[1]")
    WebElement basketButton;

    @FindBy(xpath = "//a[@class='button-style button-style_auxiliary button-style_base-alter product-recommended__button product-recommended__button_increment']")
    WebElement addButton;

    @FindBy(xpath = "(//*[@id='container']//descendant::div[contains(text(), 'р.')])[2]")
    WebElement priceText;

    @FindBy(xpath = "(//div[@class='product-recommended__link product-recommended__link_primary'])[2]")
    WebElement priceTextInBasket;

    @FindBy(xpath ="//div[@class='product-recommended__link product-recommended__link_primary']//descendant::span")
    WebElement initialPriceInBasket;

    @FindBy(xpath = "//div[@class='product-recommended__link product-recommended__link_primary']//descendant::span")
    WebElement newPriceTextField;

    @FindBy(xpath = "//a[@class='button-style button-style_auxiliary button-style_base-alter product-recommended__button product-recommended__button_decrement']")
    WebElement removeButton;

    @FindBy(xpath = "//div[@class='product-recommended__sidebar-close']")
    WebElement closeButton;

    @FindBy(xpath = "//a[@class='auth-bar__item auth-bar__item--cart']")
    WebElement cartButton;

    @FindBy(xpath = "(//div[@class='cart-form__control'])[1]")
    WebElement basketRemovalButton;

    public SearchPage returnHomePage() {
        driver.navigate().to("https://www.onliner.by");
        return this;
    }

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Click Catalog button")
    public SearchPage switchToCatalog() {
        catalogButton.click();
        return this;
    }

    @Step("Input search value")
    public SearchPage searchItem(String item) {
        searchField.sendKeys(item);
        return this;
    }

    @Step("Select item based on search results")
    public SearchPage selectItem() {
        driver.switchTo().frame(iframe);
        searchResult.click();
        return this;
    }

    @Step("Accept locality")
    public SearchPage acceptLocality() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", localityField);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(bannerAccept));
            acceptButton.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Add item to basket")
    public SearchPage addToBasket() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", basketButton);
        List<String> price = new ArrayList<>();
        price.add(priceText.getText());
        Assert.assertEquals(price.get(0), priceTextInBasket.getText());
        return this;
    }

    @Step("Increase the number of items")
    public SearchPage addQuantity() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addButton));
        executor.executeScript("arguments[0].click();", addButton);
        String price = initialPriceInBasket.getText();
        try {
            double aDoublePrim = Double.parseDouble(price);
            double a =  aDoublePrim + aDoublePrim;
            String newPrice = String.valueOf(a);
            Assert.assertEquals(newPrice, newPriceTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
     return this;
    }

    @Step("Remove item")
    public SearchPage removeQuantity() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", removeButton);
        return this;
    }

    @Step("Switch to shopping cart")
    public SearchPage switchToCart() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(cartButton));
            cartButton.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Click close window button")
    public SearchPage closeWindow() {
        closeButton.click();
        return this;
    }

    
    @Step("Delete item from basket")
    public SearchPage deleteItem() {
        Actions action = new Actions(driver);
        action.moveToElement(basketRemovalButton).doubleClick(basketRemovalButton).build().perform();
        return this;
    }


    @Step("Verify that user is logged in")
    public boolean isItemDeleted() {
        WebElement confirm = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.xpath("//div[@class='cart-form__description cart-form__description_primary cart-form__description_base-alter cart-form__description_condensed-extra']")));
        return confirm.getText().contains("удалили");
    }
}








