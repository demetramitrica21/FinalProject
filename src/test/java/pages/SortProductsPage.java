package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class SortProductsPage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By closePopUpLocator = By.xpath("//button[@aria-label='Inchide']");
    private By dressesEventsLocator = By.xpath("//a[@href='https://www.anasofia.ro/collections/rochii-elegante'" +
            " and @class='Heading u-h6']");
    private By sortButtonLocator = By.xpath("//button[@aria-label='Afisati sortarea dupa']");
    private By ascendingPriceButton = By.xpath("//button[@data-value='price-ascending']");
    private By priceElementsLocator = By.xpath("//div[@class='ProductItem__PriceList  Heading']");

    public SortProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that HomePage is properly loaded");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test case 1: Sort dresses by Price from LOW to HIGH;

    public void sortDressesByPriceFromLowToHigh() {
        logInfo(INFO_STEP, "User starts sorting dresses by price from low to high");
        logInfo(INFO_STEP, "Accepting cookies if visible");
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        logInfo(INFO_STEP, "Closing pop-up if present");
        elementMethods.closePopUpBox(closePopUpLocator);
        logInfo(INFO_STEP, "User clicks 'ROCHII EVENIMENTE' menu");
        elementMethods.clickElement(dressesEventsLocator);
        logInfo(INFO_STEP, "Scrolling to bring sort options into view");
        elementMethods.scrollPageDown("300");
        logInfo(INFO_STEP, "User clicks on the sort button");
        elementMethods.clickElement(sortButtonLocator);
        logInfo(INFO_STEP, "User selects -> 'Price: Low to High' option");
        elementMethods.clickElement(ascendingPriceButton);
    }

    public void validateThatPricesAreSortedLowToHigh() {
        logInfo(PASS_STEP,"Validate that prices are sorted from low to high");
        // Wait a few seconds to let the page reload prices
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Re-fetch the updated price elements after sorting
        List<WebElement> priceElements = driver.findElements(priceElementsLocator);
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String rawText = priceElement.getText(); // Example: "149,99 Lei"
            rawText = rawText.replaceAll("[^0-9,]", "").replace(",", ".");
            if (!rawText.isEmpty()) {
                try {
                    prices.add(Double.parseDouble(rawText));
                } catch (NumberFormatException e) {
                    logInfo(INFO_STEP, "Failed to parse price text: " + rawText);
                }
            }
        }
        logInfo(INFO_STEP, "Collected prices after sorting: " + prices);
        // Check that prices are sorted ascending
        for (int i = 0; i < prices.size() - 1; i++) {
            double current = prices.get(i);
            double next = prices.get(i + 1);
            logInfo(INFO_STEP, "Comparing prices: " + current + " <= " + next);
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices are not sorted: " + prices.get(i) + " > " + prices.get(i + 1));
        }
        //The test will display only the prices that are not on sale;
        logInfo(PASS_STEP, "All product prices are correctly sorted from low to high");
    }
}
