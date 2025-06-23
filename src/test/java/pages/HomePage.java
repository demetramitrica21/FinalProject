package pages;

import constants.MenuConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "ANASOFIA", "Page is not loaded properly");
    }

    //Test case1: Check if all menus from ANASOFIA page load properly;

    public void checkIfAllMenusFromHomepageAreLoadedProperly() {
        elementMethods.acceptCookiesPolicy(acceptCookiesLocator);

        for (Map.Entry<String, String> menuEntry : MenuConstants.menuTitles.entrySet()) {
            String menuName = menuEntry.getKey();
            String expectedTitle = menuEntry.getValue();

            boolean menuFoundAndClicked = elementMethods.clickElementByTextIgnoreCase(allMenusLocator, menuName);
            Assert.assertTrue(menuFoundAndClicked, "Menu'" + menuName + "' wasn't found on Homepage!");

            String actualPageTitle = elementMethods.getTextFromElement(menusTitleLocator);
            Assert.assertTrue(
                    actualPageTitle.toLowerCase().contains(expectedTitle.toLowerCase()),
                    "The page for the menu '" + menuName + "' didn't load properly! "
                            + "Expected title'" + expectedTitle + "', but was found: '" + actualPageTitle + "'");
        }
    }
}
