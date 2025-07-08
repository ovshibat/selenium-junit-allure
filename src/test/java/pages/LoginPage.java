package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        WebElement usernameInput = WaitHelper.waitForVisible(driver, By.id("user-name"));
        WebElement passwordInput = WaitHelper.waitForVisible(driver, By.id("password"));
        WebElement loginBtn = WaitHelper.waitForClickable(driver, By.id("login-button"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public String getErrorMessage() {
        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        return error.getText();
    }
}