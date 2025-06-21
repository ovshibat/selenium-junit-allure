package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // locators
    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By cartItemPrices = By.className("inventory_item_price");
    private By continueShoppingButton = By.id("continue-shopping");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Check if the user is on the cart page
    public boolean isOnCartPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://www.saucedemo.com/cart.html");
    }

    // Check if the cart is empty
    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size() == 0;
    }

    // Get list of all item names in cart
    public List<String> getCartItemNames() {
        // Find all web elements that contain item names
        List<WebElement> nameElements = driver.findElements(cartItemNames);
        // Create an empty String list to store the actual text names
        List<String> itemNames = new ArrayList<>();

        for (int i = 0; i < nameElements.size(); i++) {
            WebElement nameElement = nameElements.get(i);
            String itemName = nameElement.getText();
            itemNames.add(itemName);
        }
            return itemNames;
    }

    // Check if the specific item is displayed in cart
    public boolean isItemDisplayedInCart(String productName) {
        // Get all items currently in the cart
        List<String> cartItems = getCartItemNames();

        // Search through the cart for our product
        if (cartItems.contains(productName)) {
            return true;
        } else {
            return false;
        }
    }

    public String getItemPrice(String productName) {
        // Get all cart item containers and assign them into the list
        List<WebElement> allCartItems = driver.findElements(cartItems);

        for (int i = 0; i < allCartItems.size(); i++) {
            // Get the item at position i (0, 1, 2, etc)
            WebElement currentItem = allCartItems.get(i);

            // Find the name element inside this item
            WebElement nameElement = currentItem.findElement(cartItemNames);
            String itemName = nameElement.getText();

            // check if this is the item we want
            boolean foundTheItem = itemName.equals(productName);

            if (foundTheItem == true) {
                WebElement priceElement = currentItem.findElement(cartItemPrices);
                String price = priceElement.getText();
                return price;
            }
        }

        return "";
    }

    public void removeItemFromCart(String productToRemove) {
        // Get all cart items on the page and assign them to the List
        List<WebElement> cartItemElements = driver.findElements(cartItems);

        // Loop through each cart item
        for (int i = 0; i < cartItemElements.size(); i++) {
            WebElement cartItem = cartItemElements.get(i);
            String itemName = cartItem.findElement(cartItemNames).getText();

            //Check if this is the item I want to remove
            if (itemName.equals(productToRemove)) {
                WebElement removeButton = cartItem.findElement(By.xpath(".//button[contains(@id, 'remove')]"));

                // click the remove button
                removeButton.click();

                // this stops the search for items and we removed the item
                break;
            }
        }
    }

    public void clickContinueShopping() {
        WebElement ContinueShopping = driver.findElement(continueShoppingButton);
        ContinueShopping.click();
    };

}
