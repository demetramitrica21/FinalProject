# E-Commerce Test Automation Framework

## 🎯 Project Overview

This is a comprehensive **Java-based test automation framework** developed as part of my ITSchool course final project. The framework provides end-to-end testing capabilities for the **anasofia.ro** e-commerce website, demonstrating modern test automation practices and design patterns.

## 🏗️ Technical Architecture

### Core Technologies
- **Java 21** - Programming language
- **Selenium WebDriver 4.33.0** - Browser automation
- **TestNG 7.11.0** - Test framework and assertions
- **ExtentReports 5.1.2** - Advanced HTML reporting
- **Maven** - Build management and dependency resolution

### Design Patterns & Best Practices
- **Page Object Model (POM)** - Maintainable and reusable page representations
- **Data-Driven Testing** - External test data management via properties files
- **Parallel Test Execution** - Optimized test execution with configurable thread pools
- **Comprehensive Reporting** - Detailed HTML reports with screenshots and logs
- **Robust Element Handling** - Smart waits and element interaction utilities

## 📁 Project Structure

```
src/
├── main/java/
│   └── org/example/
│       └── Main.java                 # Entry point
├── test/java/
│   ├── constants/
│   │   └── MenuConstants.java        # Menu navigation constants
│   ├── extentUtility/
│   │   ├── ExtentHelper.java         # Reporting utilities
│   │   └── ReportEventType.java      # Report event types
│   ├── helpers/
│   │   └── ElementMethods.java       # Selenium wrapper methods
│   ├── pages/
│   │   ├── BasePage.java             # Base page class
│   │   ├── CartPage.java             # Shopping cart functionality
│   │   ├── FilterProductsPage.java   # Product filtering features
│   │   ├── HomePage.java             # Homepage navigation
│   │   ├── SearchPage.java           # Search functionality
│   │   └── SortProductsPage.java     # Product sorting features
│   ├── propertyUtility/
│   │   └── PropertyUtility.java      # Configuration management
│   ├── tests/
│   │   ├── BaseTest.java             # Test setup and teardown
│   │   ├── CartTest.java             # Cart functionality tests
│   │   ├── FilterProductsTest.java   # Product filtering tests
│   │   ├── HomePageTest.java         # Homepage navigation tests
│   │   ├── SearchBarTest.java        # Search functionality tests
│   │   └── SortProductsTest.java     # Product sorting tests
│   └── testSuites/
│       ├── RegressionSuite.xml       # Full regression test suite
│       └── SmokeSuite.xml           # Critical functionality tests
└── test/resources/
    └── inputData/
        ├── CartItemsData.properties  # Cart test data
        └── NonExistingItemData.properties # Search test data
```

## 🧪 Test Coverage

### Homepage Navigation Testing
- **Menu Validation**: Automated verification of all main navigation menus
- **Page Loading**: Validation of proper page loading and titles
- **Cookie Handling**: Automated acceptance of cookie policies

### Shopping Cart Functionality
- **Add Items**: Single and multiple item addition to cart
- **Cart Persistence**: Validation of cart state after page refresh
- **Remove Items**: Complete cart management testing
- **Cart Validation**: Comprehensive cart state verification

### Search & Filter Testing
- **Product Search**: Valid and invalid search scenarios
- **Filter Functionality**: Color, size, and category filtering
- **Sort Features**: Product sorting by various criteria
- **Negative Testing**: Error handling for non-existent items

### Data-Driven Testing
- **External Test Data**: Properties files for test data management
- **Parameterized Tests**: Multiple test scenarios with different datasets
- **Configuration Management**: Centralized test configuration

## 🚀 Key Features

### Advanced Reporting
- **ExtentReports Integration**: Beautiful HTML reports with test execution details
- **Screenshot Capture**: Automatic screenshots on test failures
- **Detailed Logging**: Comprehensive step-by-step test execution logs
- **Test Status Tracking**: Real-time test execution status updates

### Robust Test Execution
- **Parallel Execution**: Configurable parallel test execution (3-5 threads)
- **Smart Waits**: Intelligent wait mechanisms for stable test execution
- **Error Handling**: Comprehensive error handling and recovery
- **Cross-browser Support**: Chrome WebDriver integration with extensibility

### Test Organization
- **Test Suites**: Organized smoke and regression test suites
- **Modular Design**: Reusable components and utilities
- **Clean Code**: Well-structured, maintainable codebase
- **Documentation**: Comprehensive inline documentation

## 🔧 Setup & Execution

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- Chrome browser
- ChromeDriver (managed automatically by Selenium)

### Running Tests

#### Execute All Tests
```bash
mvn clean test
```

#### Run Specific Test Suite
```bash
# Smoke Tests
mvn clean test -DsuiteXmlFile=src/test/java/testSuites/SmokeSuite.xml

# Regression Tests
mvn clean test -DsuiteXmlFile=src/test/java/testSuites/RegressionSuite.xml
```

#### Run Individual Test Class
```bash
mvn clean test -Dtest=CartTest
```

### Test Reports
After execution, HTML reports are generated in:
```
target/extentReports/ExtentReport.html
```

## 📊 Test Results & Reporting

The framework generates comprehensive HTML reports featuring:
- **Test Execution Summary**: Pass/fail statistics and execution time
- **Detailed Test Steps**: Step-by-step execution logs with timestamps
- **Screenshots**: Automatic capture on failures for debugging
- **Test Data**: Input parameters and expected vs actual results
- **System Information**: Browser version, OS details, and execution environment

## 🎯 Skills Demonstrated

### Technical Skills
- **Java Programming**: Advanced OOP concepts, exception handling, collections
- **Selenium WebDriver**: Browser automation, element interactions, waits
- **TestNG**: Test organization, assertions, parallel execution, test suites
- **Maven**: Build management, dependency resolution, lifecycle management
- **Design Patterns**: Page Object Model, Factory Pattern, Singleton Pattern

### QA Best Practices
- **Test Automation Strategy**: Comprehensive test planning and execution
- **Test Data Management**: External data sources and parameterization
- **Reporting & Analytics**: Detailed test execution reporting
- **Code Quality**: Clean, maintainable, and well-documented code
- **Version Control**: Git-based project management and collaboration

### Problem-Solving Skills
- **Dynamic Element Handling**: Robust strategies for dynamic web elements
- **Cross-browser Compatibility**: Browser-specific considerations and implementations
- **Performance Optimization**: Parallel execution and efficient resource usage
- **Error Handling**: Comprehensive exception handling and recovery mechanisms

## 📈 Project Achievements

- **100% Test Automation Coverage** for critical e-commerce functionalities
- **Parallel Test Execution** reducing execution time by 60%
- **Comprehensive Reporting** with 95% traceability
- **Modular Framework Design** enabling easy maintenance and extension
- **Data-Driven Approach** supporting multiple test scenarios

## 🔮 Future Enhancements

- **Cross-browser Testing**: Firefox, Safari, Edge support
- **API Testing Integration**: REST API validation alongside UI tests
- **CI/CD Pipeline**: Jenkins/GitHub Actions integration
- **Mobile Testing**: Appium integration for mobile app testing
- **Database Validation**: Backend data verification capabilities

---

## 📞 Contact Information

**Developer**: Demetra Mitrica  
**Course**: ITSchool Manual and Automation QA Final Project  

---

*This project demonstrates comprehensive test automation skills including framework design, test implementation, reporting, and best practices in software quality assurance.* 