package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest extends BaseTest {

    @Test
    public void testProductListLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        assertTrue(inventoryPage.isAtInventoryPage(), "User should be on inventory page after login");
        assertEquals(6, inventoryPage.getProductCount(), "There should be 6 products displayed");
    }

    @Test
    public void testSortAToZ() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isAtInventoryPage());

        // Select A to Z
        inventoryPage.selectSortOption("Name (A to Z)");

        // Get displayed product names
        List<String> actualNames = inventoryPage.getProductNames();

        // Create a sorted copy of the list for comparison
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(String::compareTo);

        assertEquals(expectedNames, actualNames, "Product names should be sorted alphabetically");
    }
}

