package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // ❌ No hardcoded chromedriver path or Chrome binary.
        // Selenium Manager (built into Selenium 4.6+) will resolve the driver.

        // (Optional) Helpful flags; uncomment if you need them:
        // options.addArguments("--start-maximized");              // Windows/Linux
        // options.addArguments("--window-size=1440,900");         // macOS typical
        // options.addArguments("--disable-gpu");                  // CI stability
        // options.addArguments("--headless=new");                 // headless runs

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void loginAs(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    public void loginAsStandardUser() { loginAs(Users.STANDARD, Users.PASSWORD); }
    public void loginAsLockedOutUser() { loginAs(Users.LOCKED_OUT, Users.PASSWORD); }
    public void loginAsProblemUser() { loginAs(Users.PROBLEM, Users.PASSWORD); }
    public void loginAsPerformanceGlitchUser() { loginAs(Users.PERFORMANCE, Users.PASSWORD); }
    public void loginAsErrorUser() { loginAs(Users.ERROR, Users.PASSWORD); }
    public void loginAsVisualUser() { loginAs(Users.VISUAL, Users.PASSWORD); }
}