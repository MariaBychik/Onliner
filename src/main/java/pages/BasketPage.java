package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {
    public WebDriver driver;

    @FindBy(xpath = "(//div[@class='cart-form__control'])[1]")
    WebElement basketRemovalButton;

    public BasketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Delete item from basket")
    public BasketPage deleteItem() {
        Actions action = new Actions(driver);
        action.moveToElement(basketRemovalButton).click(basketRemovalButton).build().perform();
        return this;
    }

    @Step("Verify that item is deleted")
    public boolean isItemDeleted() {
        WebElement confirm = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.xpath("//div[@class='cart-form__description cart-form__description_primary cart-form__description_base-alter cart-form__description_condensed-extra']")));
        return confirm.getText().contains("удалили");
    }
}
