package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.PASS_STEP;

public class ProductsFilterPage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By closePopUpLocator = By.xpath("//button[@aria-label='Inchide']");
    private By dressesEventsLocator = By.xpath("//a[@href='https://www.anasofia.ro/collections/rochii-elegante'" +
            " and @class='Heading u-h6']");
    private By dressColourLocator = By.xpath("//label[@for='sidebar-filter-v-m-my_fields-culoarerosu']");
    private By dressSizeLocator = By.xpath("//label[@for='sidebar-filter-v-m-my_fields-marimeas']");
    private By firstProductLocator = By.xpath("//img[@data-media-id='49681985438034']");
    private By sizeLocator = By.xpath("//label[text()='S']");
    private By colourLocator = By.xpath("//label[text()='rosu']");



    public ProductsFilterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP, "Validate that the ANASOFIA page is properly loaded!");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test case 1: Filter Products by Color and Size;

    public void filterProductsBySizeAndColor() {
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        elementMethods.closePopUpBox(closePopUpLocator);
        elementMethods.clickElement(dressesEventsLocator);
        elementMethods.scrollPageDown("400");
        elementMethods.clickElement(dressColourLocator);
        elementMethods.scrollPageDown("400");
        elementMethods.clickElement(dressSizeLocator);
        elementMethods.clickElement(firstProductLocator);
        elementMethods.scrollPageDown("300");
    }

        public void validateThatProductsContainRedColourAndSSize(){
            Assert.assertTrue(elementMethods.getTextFromElement(colourLocator).contains("rosu"),
                    "Product does not contain expected colour: Rosu");
            Assert.assertTrue(elementMethods.getTextFromElement(sizeLocator).contains("S"),
                    "Product does not contain expected size: S");
        }
}
