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

    private By productItems = By.className("inventory_item");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }

    public boolean isAtInventoryPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public void selectSortOption(String visibleText) {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(productNames);
        return nameElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (int i = 0; i < priceElements.size(); i++) {
            WebElement priceElement = priceElements.get(i);
            String priceText = priceElement.getText(); // Example: "$29.99"
            String cleanText = priceText.replace("$", ""); // Remove the "$" sign
            double price = Double.parseDouble(cleanText); // Convert to double
            prices.add(price); // Add to the list
        }

        return prices;
    }
}