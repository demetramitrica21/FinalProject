package tests;

import org.testng.annotations.Test;
import pages.FilterProductsPage;

public class FilterProductsTest extends BaseTest {

    @Test
    public void filterProductsByColorAndSize(){
        FilterProductsPage productsFilterPage= new FilterProductsPage(driver);
        productsFilterPage.isPageLoaded();
        productsFilterPage.filterProductsBySizeAndColor();
        productsFilterPage.validateThatProductsIncludeRedColourAndSSize();
    }
}
