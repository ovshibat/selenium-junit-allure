package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private WebDriver driver;

    private By confirmationHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("checkout-complete.html");
    }

    public String getConfirmationHeader() {
        return driver.findElement(confirmationHeader).getText();
    }
}
