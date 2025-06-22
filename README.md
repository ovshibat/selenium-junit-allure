# Selenium Test Automation

Automated tests for SauceDemo website using Selenium WebDriver and JUnit 5.

## What This Project Does

Tests the main features of https://www.saucedemo.com/:
- Login functionality
- Product browsing and sorting
- Shopping cart operations
- Product details

## Technologies

- Java 17
- Selenium WebDriver 4.33.0
- JUnit 5
- Maven
- Allure Reports

## Setup

1. **Prerequisites**
   - Java 17+
   - Maven 3.6+
   - Chrome browser

2. **Install**
   ```bash
   git clone <your-repo>
   cd selenium-junit-allure
   mvn clean install
   ```

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=LoginTest

# Generate reports
mvn test
mvn allure:serve
```

## Test Structure

```
src/test/java/
├── pages/          # Page objects (LoginPage, CartPage, etc.)
├── tests/          # Test classes (LoginTest, CartTest, etc.)
└── utils/          # Helper classes (BaseTest, Users)
```

## Available Tests

**LoginTest** - User authentication
**InventoryTest** - Product listing and sorting
**CartTest** - Shopping cart functionality
**ProductDetailsTest** - Individual product interactions

## Test Users

All tests use predefined users from the SauceDemo site:
- `standard_user` / `secret_sauce` (main test user)
- `locked_out_user` / `secret_sauce` (for error scenarios)

## Common Commands

```bash
# Quick single test
mvn test -Dtest=LoginTest#testValidLogin

# Run and view report
mvn clean test && mvn allure:serve

# Clean everything
mvn clean
```

## Troubleshooting

- **Chrome issues**: Make sure Chrome is installed and up to date
- **Test failures**: Check if https://www.saucedemo.com/ is accessible
- **Dependency issues**: Run `mvn clean install -U`

That's it! The tests use Page Object Model and should be easy to extend.