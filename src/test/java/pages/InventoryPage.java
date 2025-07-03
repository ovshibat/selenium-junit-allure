package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    // Locators for elements on the inventory page
    private By productItems = By.className("inventory_item");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtn = By.className("btn_inventory");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");

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

    // Get the price string for a specific product
    public String getProductPrice(String productName) {
        List<WebElement> products = driver.findElements(productItems);

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            String name = product.findElement(productNames).getText();
            if (name.equals(productName)) {
                return product.findElement(productPrices).getText();
            }
        }

        return "";
    }

    public void addProductToCart(String productName) {
        List<WebElement> products = driver.findElements(productItems);

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            String name = product.findElement(productNames).getText();

            if (name.equals(productName)) {
                product.findElement(addToCartButtn).click();
                break;
            }
        }
    }

    public int getCartItemCount() {
        List<WebElement> cartCountItems = driver.findElements(cartBadge);

        if (cartCountItems.isEmpty()) {
            return 0;
        } else {
            String countText = cartCountItems.get(0).getText();
            return Integer.parseInt(countText);
        }
    }

    public void openProductDetails(String productName) {
        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            String name = product.findElement(By.className("inventory_item_name")).getText();

            if (name.equalsIgnoreCase(productName)) {
                product.findElement(By.className("inventory_item_name")).click();
                break;
            }
        }
    }

    public boolean isAddToCartButtonVisible(String productName) {
        List<WebElement> allProducts = driver.findElements(productItems); // get all products on the page

        for (int i = 0; i < allProducts.size(); i++) {
            WebElement currentProduct = allProducts.get(i);

            String currentProductName = currentProduct.findElement(productNames).getText(); // get the name of this product

            // Check if this is the product we're looking for
            if (currentProductName.equals(productName)) {
                List<WebElement> addToCartButtons = currentProduct.findElements(By.xpath(".//button[contains(@id, 'add-to-cart')]"));

                // Check if we found any "Add to Cart" buttons
                if (addToCartButtons.isEmpty()) {
                    return false;
                } else {
                    WebElement addToCartButton = addToCartButtons.get(0);
                    return addToCartButton.isDisplayed();
                }
            }
        }

        return false;
    }

    public void removeProductFromCart(String productName) {
        List<WebElement> products = driver.findElements(productItems);

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            String name = product.findElement(productNames).getText();

            if (name.equals(productName)) {
                // Look for the remove button within this product
                List<WebElement> removeButtons = product.findElements(By.xpath(".//button[contains(@id, 'remove')]"));
                if (!removeButtons.isEmpty()) {
                    removeButtons.get(0).click();
                    break;
                }
            }
        }
    }

    // Click on cart icon to navigate to cart page
    public void clickCartIcon() {
        WebElement cartIconElement = driver.findElement(cartIcon);
        cartIconElement.click();
    }

    public boolean containsProgrammingCode(String text) {
        if (text.contains("()")) return true;
        if (text.contains("{}")) return true;
        if (text.contains("[]")) return true;
        if (text.contains("=>")) return true;
        if (text.contains("==")) return true;
        if (text.contains("!=")) return true;
        if (text.matches(".*\\.\\w+\\(.*")) return true;

        return false;
    }

    // Helper method to check if text is readable (basic validation)
    public boolean isReadableText(String text) {
        // Text should not be empty
        if (text == null || text.trim().isEmpty()) return false;

        // Text should contain mostly letters and spaces
        // Allow some special characters but not too many programming symbols
        String allowedPattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 -_.,!?()&";

        return text.matches(allowedPattern);
    }

}
