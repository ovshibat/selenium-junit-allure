package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {
    private WebDriver driver;

    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
}
