package tests;

import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchPage;

@Listeners({TestListener.class})
public class SearchTest extends BaseTest{

    @Test(priority = 1,description="Search items with valid values")
    @Description("Test Description: Search test with authorization in the account")
    public void validSearch() throws Exception {

        SearchPage search = new SearchPage(driver);
        search.returnHomePage()
                .switchToCatalog()
                .searchItem(getTestData("item"))
                .selectItem()
                .acceptLocality()
                .addToBasket()
                .addQuantity()
                .removeQuantity()
                .closeWindow()
                .switchToCart()
                .deleteItem();
        Assert.assertTrue(search.isItemDeleted(), "Item is not deleted");
    }
}
