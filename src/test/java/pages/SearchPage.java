package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test Case 1: Searching for a non-existing product should have an error message;

    public void searchNonExistingProduct(String productName) {
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        elementMethods.clickElement(clickSearchIconLocator);
        elementMethods.fillElement(typeProductInSearchBarLocator, productName);
        elementMethods.waitForElement(searchResultsLocator);
    }

    public void validateThatAnErrorMessageOccurs() {
        Assert.assertTrue(elementMethods.isLocatorDisplayed(errorMessageLocator), "There are multiple results for search");
    }
}
