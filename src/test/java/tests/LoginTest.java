package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}