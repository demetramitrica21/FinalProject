package pages;

import constants.MenuConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class HomePage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By allMenusLocator = By.xpath("//a[@class='Heading u-h6']");
    private By menusTitleLocator = By.xpath("//header[@class=\"PageHeader\"]//h1");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP, "Validate that Homepage is properly loaded");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test case1: Check if all menus from ANASOFIA page load properly;

    public void checkIfAllMenusFromHomepageAreLoadedProperly() {
        logInfo(INFO_STEP, "User accepts cookies if the banner is present");
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);
        logInfo(INFO_STEP, "User starts verifying that all main menu items are clickable and direct to the correct pages");
        for (Map.Entry<String, String> menuEntry : MenuConstants.menuTitles.entrySet()) {
            String menuName = menuEntry.getKey();
            String expectedTitle = menuEntry.getValue();
            logInfo(INFO_STEP, "Looking for menu: '" + menuName + "'");
            boolean menuFoundAndClicked = elementMethods.clickElementByTextIgnoreCase(allMenusLocator, menuName);
            Assert.assertTrue(menuFoundAndClicked, "Menu'" + menuName + "' wasn't found on Homepage!");
            logInfo(INFO_STEP, "Clicked on menu: '" + menuName + "'. Waiting for page to load...");
            String actualPageTitle = elementMethods.getTextFromElement(menusTitleLocator);
            logInfo(INFO_STEP, "Expected page title: '" + expectedTitle + "', Actual title found: '" + actualPageTitle + "'");
            Assert.assertTrue(actualPageTitle.toLowerCase().contains(expectedTitle.toLowerCase()),
                    "The page for the menu '" + menuName + "' didn't load properly! "
                            + "Expected title'" + expectedTitle + "', but was found: '" + actualPageTitle + "'");
            logInfo(PASS_STEP, "Validate that menu '" + menuName + "' correctly navigates to page with title: '" + actualPageTitle + "'");
        }
        logInfo(PASS_STEP, "All menus have been successfully verified.");
    }
}
