package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;

    // Locators for elements on the inventory page
    private By productItems = By.className("inventory_item");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");

    // Constructor to initialize WebDriver
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Returns the number of products listed on the page
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }

    // Checks if we are on the inventory page
    public boolean isAtInventoryPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://www.saucedemo.com/inventory.html");
    }

    // Selects a sorting option from the dropdown (e.g., "Name (A to Z)")
    public void selectSortOption(String visibleText) {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select sort = new Select(dropdown);
        sort.selectByVisibleText(visibleText);
    }

    // Gets a list of all product names shown on the page
    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (int i = 0; i < nameElements.size(); i++) {
            WebElement element = nameElements.get(i);
            String name = element.getText();
            names.add(name);
        }

        return names;
    }

    // Gets a list of all product prices (as doubles)
    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (int i = 0; i < priceElements.size(); i++) {
            WebElement element = priceElements.get(i);
            String priceText = element.getText();         // Example: "$19.99"
            priceText = priceText.replace("$", "");       // Remove dollar sign
            double price = Double.parseDouble(priceText); // Convert to double
            prices.add(price);                            // Add to the list
        }

        return prices;
    }

}
