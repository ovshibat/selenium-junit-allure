package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.ProductDetailsPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void testAddItemFromProductDetailsPage() {
        // Login as a standard user
        loginAsStandardUser();

        // Verify the user is on inventory page
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isAtInventoryPage(), "The user is navigated to Inventory page");

        // Click on product details page
        inventoryPage.openProductDetails("Sauce Labs Onesie");

        // Verify the user is navigated to product details page
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.isAtProductDetailsPage();

        // Add product to cart
        productDetailsPage.addProductToCart();

        // Click on cart icon to navigate to cart page
        productDetailsPage.clickOnCartIcon();

        // Verify that selected product was added to cart page
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isOnCartPage(), "Yes, the user is on Cart page");

        cartPage.isItemDisplayedInCart("Sauce Labs Onesie");
    }

    @Test
    public void testRemoveItemFromProductDetailsPage() {
        // 1. Login as a standard user
        loginAsStandardUser();

        // 2. Initialize inventory page
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isAtInventoryPage(), "User should be on the inventory page after login");

        // 3. Open product details page for specific item
        String productName = "Sauce Labs Onesie";
        inventoryPage.openProductDetails(productName);

        // 4. Initialize product details page
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);

        // 5. Check if the user is on the product details page
        assertEquals(productName, productDetailsPage.getProductName(), "Should be correct product details page");

        // 6: Add item to cart from product details page
        productDetailsPage.addProductToCart();

        // 7: Verify cart count shows 1
        assertEquals(1, productDetailsPage.getCartItemCount(), "Cart count should be 1 after adding item from product details");

        // 8: Remove item from cart on product details page
        productDetailsPage.removeProductFromCart();

        // 9: Check that cart count becomes 0
        assertEquals(0, productDetailsPage.getCartItemCount(), "Cart count should be 0 after removing the item");

        // Step 10: Verify button text changed back to "Add to cart"
        assertTrue(productDetailsPage.isAddToCartButtonDisplayed(), "Add to cart button should be displayed after removing item");

    }
}
