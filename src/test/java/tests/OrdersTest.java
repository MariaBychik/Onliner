package tests;

import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.OrdersPage;

@Listeners({TestListener.class})
public class OrdersTest extends BaseTest {

    @Test(priority = 2,description="Search for services with valid values")
    @Description("Test Description: Search for services for customers and performers")
    public void validOrder() throws Exception {

        OrdersPage searchOrder = new OrdersPage(driver);
        SoftAssert Assert = new SoftAssert();
        searchOrder.returnHomePage()
                .switchToOrdersTab()
                .selectCustomersOrdersSection(getTestData("section"))
                .filterCustomersOrder(getTestData("order"))
                .selectCustomersOrder();
        Assert.assertTrue(searchOrder.getCustomerOrderType(), "Order is not found");
        searchOrder.switchToPerformersTab()
                .selectPerformersOrdersSection(getTestData("sectionPerformers"))
                .filterPerformersOrder(getTestData("orderPerformers"))
                .selectPerformersOrder();
        Assert.assertTrue(searchOrder.getPerformerOrderType(), "Order is not found");
        Assert.assertAll();
    }
}
