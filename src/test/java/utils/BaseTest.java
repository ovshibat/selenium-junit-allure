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
        // Explicitly tells Selenium to use Chromium
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser"); // or use `which chromium-browser` to get the correct path

        // Optionally set the chromedriver location if not in PATH
        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");

        driver = new ChromeDriver(options);
        // driver.manage().window().maximize();
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

    public void loginAsStandardUser() {
        loginAs(Users.STANDARD, Users.PASSWORD);
    }

    public void loginAsLockedOutUser() {
        loginAs(Users.LOCKED_OUT, Users.PASSWORD);
    }

    public void loginAsProblemUser() {
        loginAs(Users.PROBLEM, Users.PASSWORD);
    }

    public void loginAsPerformanceGlitchUser() {
        loginAs(Users.PERFORMANCE, Users.PASSWORD);
    }

    public void loginAsErrorUser() {
        loginAs(Users.ERROR, Users.PASSWORD);
    }

    public void loginAsVisualUser() {
        loginAs(Users.VISUAL, Users.PASSWORD);
    }
}