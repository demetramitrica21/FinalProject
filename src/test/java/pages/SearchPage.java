package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class SearchPage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By clickSearchIconLocator = By.xpath("//a[@href='/search']");
    private By typeProductInSearchBarLocator = By.xpath("//input[@type='search']");
    private By errorMessageLocator = By.xpath("//p[text()='Nu s-au gasit rezultate']");
    private By searchResultsLocator = By.id("shopify-section-predictive-search");


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that Homepage is properly loaded");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test Case 1: Searching for a non-existing product should have an error message;

    public void searchNonExistingProduct(String productName) {
        logInfo(INFO_STEP, "User starts searching for a non-existing product: '" + productName + "'");
        logInfo(INFO_STEP, "User accepts cookies if prompted");
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        logInfo(INFO_STEP, "User clicks on the search icon to open the search bar");
        elementMethods.clickElement(clickSearchIconLocator);
        logInfo(INFO_STEP, "User types the product name into the search input field");
        elementMethods.fillElement(typeProductInSearchBarLocator, productName);
        logInfo(INFO_STEP, "Waiting for the search results section to be displayed");
        elementMethods.waitForElement(searchResultsLocator);
    }

    public void validateThatAnErrorMessageOccurs() {
        logInfo(PASS_STEP, "Validate that error message is displayed when no search results are found");
        Assert.assertTrue(elementMethods.isLocatorDisplayed(errorMessageLocator), "There are multiple results for search");
    }
}
