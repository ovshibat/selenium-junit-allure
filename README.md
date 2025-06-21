# Selenium Test Automation Project

A comprehensive test automation framework for the SauceDemo e-commerce application using Selenium WebDriver, JUnit 5, and Allure reporting.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Test Scenarios](#test-scenarios)
- [Page Object Model](#page-object-model)
- [Reporting](#reporting)
- [Configuration](#configuration)

## 🎯 Project Overview

This project automates testing of the SauceDemo website (https://www.saucedemo.com/), covering key e-commerce functionalities including:

- User authentication and login scenarios
- Product inventory management
- Shopping cart operations
- Product detail page interactions
- Sorting and filtering capabilities

The framework follows the Page Object Model (POM) design pattern for better maintainability and reusability.

## 🛠 Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Programming language |
| **Selenium WebDriver** | 4.33.0 | Browser automation |
| **JUnit 5** | 5.10.2 | Testing framework |
| **Maven** | 3.x | Build and dependency management |
| **Allure** | 2.25.0 | Test reporting |
| **ChromeDriver** | Latest | Browser driver |

## 📁 Project Structure

```
selenium-junit-allure/
├── src/
│   ├── main/java/
│   │   └── Main.java                    # Basic Selenium example
│   └── test/java/
│       ├── pages/                       # Page Object Model classes
│       │   ├── CartPage.java
│       │   ├── InventoryPage.java
│       │   ├── LoginPage.java
│       │   └── ProductDetailsPage.java
│       ├── tests/                       # Test classes
│       │   ├── CartTest.java
│       │   ├── InventoryTest.java
│       │   ├── LoginTest.java
│       │   └── ProductDetailsTest.java
│       └── utils/                       # Utility classes
│           ├── BaseTest.java
│           └── Users.java
├── src/test/resources/
│   └── allure.properties               # Allure configuration
├── pom.xml                            # Maven configuration
└── README.md
```

## 📋 Prerequisites

Before running this project, ensure you have:

1. **Java 17** or higher installed
2. **Maven 3.6+** installed
3. **Chrome/Chromium browser** installed
4. **ChromeDriver** (handled automatically by Selenium Manager)

### Verify Installation

```bash
java -version
mvn -version
google-chrome --version  # or chromium-browser --version
```

## 🚀 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd selenium-junit-allure
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify setup with a simple test**
   ```bash
   mvn test -Dtest=LoginTest#testValidLogin
   ```

## ▶️ Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=CartTest
mvn test -Dtest=InventoryTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=LoginTest#testValidLogin
mvn test -Dtest=CartTest#testSingleItemInCart
```

### Run Tests with Allure Report Generation
```bash
mvn clean test
mvn allure:report
mvn allure:serve
```

## 🧪 Test Scenarios

### Login Tests (`LoginTest.java`)
- ✅ Valid user login
- ❌ Invalid credentials
- 🔒 Locked out user
- ⚠️ Empty username validation
- ⚠️ Empty password validation

### Inventory Tests (`InventoryTest.java`)
- 📦 Product list loading
- 🔤 Sorting by name (A-Z, Z-A)
- 💰 Sorting by price (Low-High, High-Low)
- ➕ Adding multiple products to cart
- ➖ Removing items from cart

### Cart Tests (`CartTest.java`)
- 🛒 Single item in cart validation
- 📋 Multiple items in cart
- 🗑️ Remove items from cart
- 📭 Empty cart validation
- 🔄 Continue shopping functionality

### Product Details Tests (`ProductDetailsTest.java`)
- 🔍 Product details page navigation
- ➕ Add/remove items from product details page
- 🔘 Button state validation

## 🏗 Page Object Model

The project uses the Page Object Model design pattern with the following structure:

### BaseTest Class
- WebDriver initialization and teardown
- Common login methods for different user types
- Browser configuration (Chromium setup)

### Page Classes
- **LoginPage**: Handles authentication functionality
- **InventoryPage**: Manages product listing and cart operations
- **CartPage**: Handles shopping cart operations
- **ProductDetailsPage**: Manages individual product interactions

### Example Usage
```java
@Test
public void testAddProductToCart() {
    loginAsStandardUser();
    
    InventoryPage inventoryPage = new InventoryPage(driver);
    inventoryPage.addProductToCart("Sauce Labs Backpack");
    
    assertEquals(1, inventoryPage.getCartItemCount());
}
```

## 📊 Reporting

The project includes Allure reporting for comprehensive test results:

### Generate and View Reports
```bash
# Run tests and generate results
mvn clean test

# Generate Allure report
mvn allure:report

# Serve report (opens in browser)
mvn allure:serve
```

### Report Features
- ✅ Test execution status
- 📊 Test statistics and trends
- 🕒 Execution timeline
- 📝 Detailed test steps
- 📸 Screenshots on failure (if configured)

## ⚙️ Configuration

### Browser Configuration
The project is configured to use Chromium by default. Modify `BaseTest.java` to change browser settings:

```java
ChromeOptions options = new ChromeOptions();
options.setBinary("/usr/bin/chromium-browser");
// Add headless mode
options.addArguments("--headless");
```

### Test Users
Predefined users are available in `Users.java`:
- `STANDARD` - standard_user
- `LOCKED_OUT` - locked_out_user
- `PROBLEM` - problem_user
- `PERFORMANCE` - performance_glitch_user
- `ERROR` - error_user
- `VISUAL` - visual_user

### Maven Configuration
Key configurations in `pom.xml`:
- Java 17 compiler settings
- Surefire plugin for test execution
- Allure dependencies and plugins
- AspectJ weaver for Allure integration

## 🐛 Troubleshooting

### Common Issues

1. **ChromeDriver not found**
   ```bash
   # Ensure Chrome/Chromium is installed and update the binary path in BaseTest.java
   ```

2. **Tests failing due to timing**
   ```bash
   # The framework uses explicit waits. Check network connectivity to https://www.saucedemo.com/
   ```

3. **Maven dependency issues**
   ```bash
   mvn clean install -U
   ```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run the test suite
6. Submit a pull request

## 📄 License

This project is for educational and testing purposes. Please refer to SauceDemo's terms of use for the target application.

---

**Happy Testing! 🚀**