package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
}