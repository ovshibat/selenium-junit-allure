package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductDetailsPage {
    public WebDriver driver;

    //Locators
    private By productName = By.className("inventory_details_name");
    private By productDescription = By.className("inventory_details_desc");
    private By productPrice = By.className("inventory_details_price");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAtProductDetailsPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("inventory-item.html");
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void addProductToCart() {
        List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(@id, 'add-to-cart')]"));
        if(addButtons.size() > 0) {
            addButtons.get(0).click();
        }
    }

    public int getCartItemCount() {
        List<WebElement> cartCountItems = driver.findElements(cartIcon);

        if (cartCountItems.size() == 0) {
            return 0;
        } else {
            String countText = cartCountItems.get(0).getText();

            return Integer.parseInt(countText);
        }
    }

    public void removeProductFromCart() {
        // Look for the remove button (ID changes based on product)
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[contains(@id, 'remove')]"));
        if (removeButtons.size() > 0) {
            removeButtons.get(0).click();
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(@id, 'add-to-cart')]"));

        // Check if we found any buttons
        if(addButtons.size() == 0) {
            return false;
        }

        // Check if the first button is visible
        WebElement firstAddButton = addButtons.get(0);
        return firstAddButton.isDisplayed();
    }

    public void clickOnCartIcon() {
        WebElement cartIconElement = driver.findElement(cartIcon);
        cartIconElement.click();
    }
}
