package tests;

import org.testng.annotations.Test;
import pages.SearchPage;
import propertyUtility.PropertyUtility;

public class SearchBarTest extends BaseTest {

    @Test
    public void searchNonExistingItem() {
        SearchPage logInPage = new SearchPage(driver);
        logInPage.isPageLoaded();
        PropertyUtility propertyUtility = new PropertyUtility("NonExistingItem");
        logInPage.searchNonExistingProduct(propertyUtility.getPropertyValue("product"));
        logInPage.validateThatAnErrorMessageOccurs();
    }
}
