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

    @Test
    public void testContinueShoppingFromCart() {
        // Step 1: Login and add item to cart
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        // Step 2: Navigate to cart page
        inventoryPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);

        assertTrue(cartPage.isOnCartPage(), "The user is on Cart Page");
        // Step 3: Click continue shopping
        cartPage.clickContinueShopping();

        // Step 4: Verify we're back on inventory page
        assertTrue(inventoryPage.isAtInventoryPage(), "Yes, the user is back on the inventory page");

        // Step 5: Verify cart still has the item (cart count should show 1)
        assertEquals(1, inventoryPage.getCartItemCount(), "The cart still has the item");
    }

    @Test
    public void testCartItemDetails() {
        // Step 1: Login and add item to cart
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        String selectedProduct = "Sauce Labs Backpack";
        inventoryPage.addProductToCart(selectedProduct);

        // Step 2: Navigate to cart page
        inventoryPage.clickCartIcon();

        // Step 3: Verify item details in cart
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "Yes, the user is on cart page");

        // Step 4: Verify all item details are present
        assertTrue(cartPage.isItemDisplayedInCart(selectedProduct));
        String itemPrice = cartPage.getItemPrice(selectedProduct);
        assertFalse(itemPrice.isEmpty(), "The product has a price");

        String itemDescription = cartPage.getItemDescription(selectedProduct);
        assertFalse(itemDescription.isEmpty(), "item should have a description");

        // Step 5: Verify quantity is correct
        int quantity = cartPage.getItemQuantity(selectedProduct);
        assertEquals(1, quantity, " item quantity should be 1");

    }

    @Test
    public void testCartItemsMatchInventoryDetails() {
        // Step 1: Login as standard user
        loginAsStandardUser();

        // Step 2: Get product details from inventory page
        InventoryPage inventoryPage = new InventoryPage(driver);
        String selectedProduct = "Sauce Labs Backpack";

        // Get price from inventory page
        String productPrice = inventoryPage.getProductPrice(selectedProduct);

        // Step 3: Add product to cart
        inventoryPage.addProductToCart(selectedProduct);

        // Step 4: Navigate to cart page
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "The user is on Cart page");

        // Step 5: Verify product details match between inventory and cart
        assertFalse(cartPage.isCartEmpty(), "No, the cart is not empty");
        assertTrue(cartPage.isItemDisplayedInCart(selectedProduct), "The selected product is displayed in the cart");
        String cartPrice = cartPage.getItemPrice(selectedProduct);
        assertEquals(productPrice, cartPrice, "Cart price should match inventory price");

    }

    @Test
    public void testResetAppStateFromCartPage() {
        // Step 1: Login and add items to cart
        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie");

        for (int i = 0; i < productsToAdd.size(); i++){
            String product = productsToAdd.get(i);
            inventoryPage.addProductToCart(product);
        }
        // Step 2: Navigate to cart page
        CartPage cartPage = new CartPage(driver);
        inventoryPage.clickCartIcon();
        assertTrue(cartPage.isOnCartPage(), "yes, the user is on the Cart Page");
        // Step 3: Verify items are in cart
        assertTrue(cartPage.isItemDisplayedInCart("Sauce Labs Backpack"));
        assertTrue(cartPage.isItemDisplayedInCart("Sauce Labs Bolt T-Shirt"));
        assertTrue(cartPage.isItemDisplayedInCart("Sauce Labs Onesie"));
        // Step 4: Reset app state from cart page

        // Step 5: Verify cart is empty
        // Step 6: Go back to inventory and verify button states

    }

}
