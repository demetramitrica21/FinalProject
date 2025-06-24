package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class FilterProductsPage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By closePopUpLocator = By.xpath("//button[@aria-label='Inchide']");
    private By dressesEventsLocator = By.xpath("//a[@href='https://www.anasofia.ro/collections/rochii-elegante'" +
            " and @class='Heading u-h6']");
    private By dressColourLocator = By.xpath("//label[@for='sidebar-filter-v-m-my_fields-culoarerosu']");
    private By dressSizeLocator = By.xpath("//label[@for='sidebar-filter-v-m-my_fields-marimeas']");
    private By productsLocator = By.xpath("//div[@class='CollectionInner__Products']//div[@class='ProductItem ']");
    private By sizeLocator = By.xpath("//label[text()='S']");
    private By colourLocator = By.xpath("//label[text()='rosu']");



    public FilterProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP, "Validate that the Homepage is properly loaded!");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test case 1: Filter Products by Color and Size;

    public void filterProductsBySizeAndColor() {
        logInfo(INFO_STEP, "User accepts cookies if displayed");
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        logInfo(INFO_STEP, "User closes any visible pop-up");
        elementMethods.closePopUpBox(closePopUpLocator);
        logInfo(INFO_STEP, "Navigating to 'ROCHII EVENIMENTE' menu");
        elementMethods.clickElement(dressesEventsLocator);
        logInfo(INFO_STEP, "Scrolling down to reach filters");
        elementMethods.scrollPageDown("400");
        logInfo(INFO_STEP, "Applying 'Red' color filter");
        elementMethods.clickElement(dressColourLocator);
        logInfo(INFO_STEP, "Scrolling again to reach size filter");
        elementMethods.scrollPageDown("400");
        logInfo(INFO_STEP, "Applying 'S' size filter");
        elementMethods.clickElement(dressSizeLocator);
        logInfo(INFO_STEP, "Waiting for filtered results to load");
        elementMethods.setSleep(3000L);
    }

        public void validateThatProductsIncludeRedColourAndSSize(){
            List<WebElement> products = driver.findElements(productsLocator);
            logInfo(INFO_STEP, "Found " + products.size() + " products after filtering. Verifying each for color 'rosu' and size 'S'");
            for (int i = 0; i < products.size(); i++) {
                WebElement product = products.get(i);
                logInfo(INFO_STEP, "Opening product #" + (i + 1));
                elementMethods.clickElement(product);
                elementMethods.scrollPageDown("300");
                String actualColor = elementMethods.getTextFromElement(colourLocator);
                logInfo(INFO_STEP, "Verifying product color. Expected: 'rosu', Found: '" + actualColor + "'");
                Assert.assertTrue(actualColor.contains("rosu"), "Product does not contain expected color: 'rosu'");
                String actualSize = elementMethods.getTextFromElement(sizeLocator);
                logInfo(INFO_STEP, "Verifying product size. Expected: 'S', Found: '" + actualSize + "'");
                Assert.assertTrue(actualSize.contains("S"), "Product does not contain expected size: 'S'");
                logInfo(PASS_STEP, "Product #" + (i + 1) + " passed both color and size checks.");
                driver.navigate().back();
            }
            logInfo(PASS_STEP, "All filtered products contain the expected color 'rosu' and size 'S'.");
        }
}
