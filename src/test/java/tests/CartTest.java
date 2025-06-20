package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        for (int i = 0; i < productsToAdd.size(); i++) {
            String productName = productsToAdd.get(i);
            inventoryPage.addProductToCart(productName);
        }

        // Click on cart and check the user is on the cart page
        inventoryPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "The user is on the cart page");

        // Verify the added items are in the cart page
        List<String> cartItems = cartPage.getCartItemNames();
        for (int i = 0; i < productsToAdd.size(); i++) {
            String expectedProduct = productsToAdd.get(i);
            assertTrue(cartItems.contains(expectedProduct), "Product not found in cart: " + expectedProduct);
        }

        // Check item details are correct
        for (int i = 0; i < productsToAdd.size(); i++) {
            String productName = productsToAdd.get(i);
            assertTrue(cartPage.isItemDisplayedInCart(productName), "Product should be displayed in cart: " + productName);
            assertFalse(cartPage.getItemPrice(productName).isEmpty(), "Product should have a price displayed: " + productName);
        }

        // Verify that the item prices are not empty
        for (int i = 0; i < productsToAdd.size(); i++) {
            String productName = productsToAdd.get(i);
            assertTrue(!cartPage.getItemPrice(productName).isEmpty(), "Item price missing for: " + productName);
        }
    }

    @Test
    public void testEmptyCartValidation() {
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "The user is on the cart page");
        assertTrue(cartPage.isCartEmpty(), "The cart page is empty");

        List<String> itemsInCart = cartPage.getCartItemNames();
        assertEquals(0, itemsInCart.size(), "Cart doesn't contain any items");
    }

    @Test
    public void testRemoveItemFromCart() {
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        List<String> itemsInTheCart = cartPage.getCartItemNames();
        assertEquals(1, itemsInTheCart.size(), "There should be one item");

        cartPage.removeItemFromCart("Sauce Labs Backpack");

        List<String> itemsAfterRemoval = cartPage.getCartItemNames();
        assertEquals(0, itemsAfterRemoval.size(), "Cart is empty");
        assertFalse(itemsAfterRemoval.contains("Sauce Labs Backpack"), "Cart should not contain the removed product");
    }
}
