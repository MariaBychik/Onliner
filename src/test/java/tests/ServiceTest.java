package tests;

import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ServicePage;

@Listeners({TestListener.class})
public class ServiceTest extends BaseTest {

    @Test(priority = 0,description="Search for services for customers with valid values")
    @Description("Test Description: Search for services for customers and performers")
    public void validSearchOrder() throws Exception {

        ServicePage searchOrder = new ServicePage(driver);
        SoftAssert Assert = new SoftAssert();
        searchOrder.returnHomePage()
                .switchToOrdersTab()
                .selectCustomersOrdersSection(getTestData("section"))
                .filterCustomersOrder(getTestData("order"))
                .selectCustomersOrder();
        Assert.assertTrue(searchOrder.getCustomerOrderType(), "Order is not found");
    }


        @Test(priority = 1,description="Search for services for customers with valid values")
        @Description("Test Description: Search for services for customers and performers")
        public void validSearchPerformer() throws Exception {

            ServicePage searchPerformer = new ServicePage(driver);
            SoftAssert Assert = new SoftAssert();
            searchPerformer.switchToPerformersTab()
                .selectPerformersOrdersSection(getTestData("sectionPerformers"))
                .filterPerformersOrder(getTestData("orderPerformers"))
                .selectPerformersOrder();
        Assert.assertTrue(searchPerformer.getPerformerOrderType(), "Order is not found");
        Assert.assertAll();
    }
}
