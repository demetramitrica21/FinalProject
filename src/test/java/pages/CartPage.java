package pages;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class CartPage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By clickSearchIconLocator = By.xpath("//a[@href='/search']");
    private By typeProductInSearchBarLocator = By.xpath("//input[@type='search']");
    private By searchResultsLocator = By.xpath("//div[@class='Search__Results']");
    private By desiredItemOneLocator = By.xpath("//div[@class='ProductItem ']");
    private By closePopUpLocator = By.xpath("//button[@aria-label='Inchide']");
    private By chooseDesiredSizeLocator = By.xpath("//label[text()='S-L' or text()='S']");
    private By addItemToCartButtonLocator = By.xpath("//span[text()='Adauga in cos']");
    private By sideBarCartLocator = By.id("sidebar-cart");
    private By cartItemLocator = By.xpath("//div[@class='CartItem']"); //general xpath for all items added in cart;
    private By cartIconLocator = By.xpath("//a[@data-drawer-id='sidebar-cart']");
    private By closeCartLocator = By.xpath("//button[@aria-label='Cos inchis']");
    private By removeItemLocator = By.xpath("//a[@data-action='remove-item']");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP, "Validate that the HomePage is properly loaded!");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test Case 1: Add a single item to cart;

    public void addItemToCart(String productName) {
        logInfo(INFO_STEP, "User searches for product: " + productName);
        searchProduct(productName);
        logInfo(INFO_STEP, "Waiting for search results to appear");
        elementMethods.waitForElement(searchResultsLocator);
        logInfo(INFO_STEP, "User clicks on the first desired product");
        elementMethods.clickElement(desiredItemOneLocator);
        logInfo(INFO_STEP, "User scrolls to product size section");
        elementMethods.scrollPageDown("350");
        logInfo(INFO_STEP, "User selects size 'S'");
        elementMethods.clickElement(chooseDesiredSizeLocator);
        logInfo(INFO_STEP, "User clicks 'Add to cart' button");
        elementMethods.clickElement(addItemToCartButtonLocator);
        //wait for the sidebar to show up;
        elementMethods.waitForElement(sideBarCartLocator);
        //wait for the item to be visible in the cart;
        elementMethods.waitForElement(cartItemLocator);
    }

    //Test Case 2: Refresh page and verify item persistence in cart;

    public void refreshPageAndCheckCart(String productName) {
        addItemToCart(productName);
        //refresh the page;
        logInfo(INFO_STEP, "User refreshes the page to check cart persistence");
        driver.navigate().refresh();
        //click on the cart icon;
        logInfo(INFO_STEP, "Opening the cart after page refresh");
        openCart();
    }

    //Test Case 3: Add multiple items to cart;

    public void addMultipleItemsToCart(List<String> productNames) {
        for (String productName : productNames) {
            logInfo(INFO_STEP, "User adds product to cart: " + productName);
            addItemToCart(productName);
            closeCart();
        }
        logInfo(INFO_STEP, "User opens cart to check multiple items");
        openCart();
    }

    //Test Case 4: Add multiple items to cart and then remove them;

    public void addMultipleItemsToCartAndRemoveThem(List<String> productNames) {
        addMultipleItemsToCart(productNames);
        logInfo(INFO_STEP, "User removes all products from cart " + productNames);
        for (String productName : productNames) {
            logInfo(INFO_STEP, "User removes item: " + productName);
            elementMethods.clickElement(removeItemLocator);
        }
    }

    public void searchProduct(String productName) {
        logInfo(INFO_STEP, "User clicks search icon");
        elementMethods.clickElement(clickSearchIconLocator);
        logInfo(INFO_STEP, "User types product name into search bar: " + productName);
        elementMethods.fillElement(typeProductInSearchBarLocator, productName);
    }

    public void openCart() {
        logInfo(INFO_STEP, "User clicks cart icon to open cart");
        elementMethods.clickElement(cartIconLocator);
        logInfo(INFO_STEP, "User waits for cart icon and item to load");
        elementMethods.waitForElement(cartIconLocator);
        elementMethods.waitForElement(cartItemLocator);
    }

    public void closeCart() {
        logInfo(INFO_STEP, "User closes the cart");
        elementMethods.clickElement(closeCartLocator);
        logInfo(INFO_STEP, "Waiting for cart to become invisible");
        elementMethods.waitForElementInvisibility(cartItemLocator);
    }

    public void validateThatTheItemWasAddedProperlyToCart() {
        logInfo(PASS_STEP, "Validate that the item was added properly to cart!");
        Assert.assertTrue(elementMethods.isLocatorDisplayed(cartItemLocator), "The item was not added properly to cart");
    }

    public void validateThatTheItemIsStillInCartAfterPageRefresh() {
        logInfo(PASS_STEP, "Validate that the item is still in cart after page refresh");
        Assert.assertTrue(elementMethods.isLocatorDisplayed(cartItemLocator), "The item is not there after refresh");
    }

    public void validateThatTheMultipleItemsWereAddedProperlyToCart(List<String> productNames) {
        logInfo(PASS_STEP, "Validate that the multiple items were added properly to cart");
        List<WebElement> cartItems = driver.findElements(cartItemLocator);
        Assert.assertEquals(cartItems.size(), productNames.size(), "Number of added items is not equal to the number of items in cart");
    }

    public void validateThatTheCartIsEmpty(List<String> productNames) {
        logInfo(PASS_STEP, "Validate that the cart is empty");
        List<WebElement> cartItems = driver.findElements(cartItemLocator);
        Assert.assertEquals(cartItems.size(), productNames.size(), "Items are still in the cart");
    }
}

