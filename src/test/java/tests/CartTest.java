package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends BaseTest {

    @Test
    public void testSingleItemInCart() {
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        String productToAdd = "Sauce Labs Backpack";
        inventoryPage.addProductToCart(productToAdd);

        inventoryPage.clickCartIcon();

        // Check the user is on cart page and item is listed
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "Should be on cart page");

        // Check the correct item is in cart
        List<String> itemsInCart = cartPage.getCartItemNames();
        assertEquals(1, itemsInCart.size(), "Cart should contain exactly 1 item");
        assertTrue(itemsInCart.contains(productToAdd), "Cart should contain the added product");

        // Check item details are correct
        assertTrue(cartPage.isItemDisplayedInCart(productToAdd), "Product should be displayed in cart");
        assertFalse(cartPage.getItemPrice(productToAdd).isEmpty(), "Product should have a price displayed");
    }

    @Test
    public void testMultipleItemsInCart() {
        // Login as a standard user
        loginAsStandardUser();

        // Verify the user is redirected to InventoryPage
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.isAtInventoryPage();

        // Add multiple items in cart
        List<String> productsToAdd = new ArrayList<>();
        productsToAdd.add("Sauce Labs Backpack");
        productsToAdd.add("Sauce Labs Bike Light");
        productsToAdd.add("Sauce Labs Bolt T-Shirt");

        for (int i=0; i < productsToAdd.size(); i++) {
            String productName = productsToAdd.get(i);

            inventoryPage.addProductToCart(productName);
        }

        // Click on cart and check the user is on the cart page
        CartPage cartPage = inventoryPage.clickCartIcon();
        cartPage.isAtCartPage();


        // Verify the added items are in the cart page

        // Check item details are correct
    }
}
