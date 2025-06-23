package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import propertyUtility.PropertyUtility;

import java.util.List;

public class HomePageTest extends BaseTest {

    @Test
    public void checkIfAllMenusAreLoadedProperlyFlow() {
        HomePage homePage = new HomePage(driver);
        homePage.isPageLoaded();
        homePage.checkIfAllMenusFromHomepageAreLoadedProperly();
    }
}
