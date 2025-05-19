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

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "invalid_password");

        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        String actualError = loginPage.getErrorMessage();

        assertEquals(expectedError, actualError);
    }

    @Test
    public void testLockedOutLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        String actualError = loginPage.getErrorMessage();

        assertEquals(expectedError, actualError);
    }

    @Test
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        String expectedError = "Epic sadface: Username is required";
        String actualError = loginPage.getErrorMessage();

        assertEquals(expectedError, actualError);
    }

    @Test
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");

        String expectedError = "Epic sadface: Password is required";
        String actualError = loginPage.getErrorMessage();

        assertEquals(expectedError, actualError);
    }
}