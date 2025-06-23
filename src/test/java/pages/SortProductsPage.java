package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test case 1: Sort dresses by Price from LOW to HIGH;

    public void sortDressesByPriceFromLowToHigh() {
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        elementMethods.closePopUpBox(closePopUpLocator);
        elementMethods.clickElement(dressesEventsLocator);
        elementMethods.scrollPageDown("300");
        elementMethods.clickElement(sortButtonLocator);
        elementMethods.clickElement(ascendingPriceButton);
    }

    public void validateThatPricesAreSortedLowToHigh() {
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
                    System.out.println("Failed to parse price: " + rawText);
                }
            }
        }
        // Check that prices are sorted ascending
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices are not sorted: " + prices.get(i) + " > " + prices.get(i + 1));
        }
        //The test will display only the prices that are not on sale;
        System.out.println("Validated ascending prices: " + prices);
    }
}
