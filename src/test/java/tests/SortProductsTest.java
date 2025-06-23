package tests;

import org.testng.annotations.Test;
import pages.SortProductsPage;

public class SortProductsTest extends BaseTest {

    @Test
    public void sortProductByPriceFromLowToHighFlow(){
        SortProductsPage sortProductsPage= new SortProductsPage(driver);
        sortProductsPage.isPageLoaded();
        sortProductsPage.sortDressesByPriceFromLowToHigh();
        sortProductsPage.validateThatPricesAreSortedLowToHigh();
    }
}
