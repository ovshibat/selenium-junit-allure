package tests;

import org.junit.jupiter.api.Test;
import pages.*;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void testSuccessfulCheckout() {
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "Should be on cart page");

        cartPage.clickCheckout();

        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        assertTrue(infoPage.isAt(), "Should be on checkout information page");
        infoPage.fillInformation("John", "Doe", "12345");
        infoPage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        assertTrue(overviewPage.isAt(), "Should be on overview page");
        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        assertTrue(completePage.isAt(), "Should be on checkout complete page");
        assertEquals("Thank you for your order!", completePage.getConfirmationHeader());
    }
}
