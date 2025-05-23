package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
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

    @Test
    public void testSortZToA() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isAtInventoryPage());

        // Select Z to A
        inventoryPage.selectSortOption("Name (Z to A)");

        // Get displayed product names
        List<String> actualNames = inventoryPage.getProductNames();

        //Create a sorted copy of the list for comparison
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(String::compareTo); // sort A to Z
        expectedNames.sort(Comparator.reverseOrder());

        assertEquals(expectedNames, actualNames, "Product names should be sorted from Z to A");
    }

    @Test
    public void testPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isAtInventoryPage());

        // Select filter Price - Low to High
        inventoryPage.selectSortOption("Price (low to high)");

        // Get displayed product prices
        List<Double> actualPrices = inventoryPage.getProductPrices();

        //Create a sorted copy of the list for comparison
        List<Double> expectedPrices =  new ArrayList<>(actualPrices);
        expectedPrices.sort(Double::compareTo); //sort high to low

        assertEquals(expectedPrices, actualPrices, "Product prices should be sorted from low to high");
    }

    @Test
    public  void testPriceHightoLow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isAtInventoryPage());

        //Select filter Price - High to Low
        inventoryPage.selectSortOption("Price (high to low)");

        // Get product prices
        List<Double> actualPrices = inventoryPage.getProductPrices();

        //Create a sorted copy of the list for comparison
        List<Double> expectedPrices =  new ArrayList<>(actualPrices);
        expectedPrices.sort(Double::compareTo); //sort high to low
        expectedPrices.sort(Comparator.reverseOrder());

        assertEquals(expectedPrices, actualPrices, "Product prices should be sorted from high to low");
    }
}
