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

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        } else if (os.contains("win")) {
            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        }

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