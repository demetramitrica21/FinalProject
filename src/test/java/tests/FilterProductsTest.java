package tests;

import org.testng.annotations.Test;
import pages.FilterProductsPage;

public class FilterProductsTest extends BaseTest {

    @Test
    public void filterProductsByColorAndSizeFlow(){
        FilterProductsPage productsFilterPage= new FilterProductsPage(driver);
        productsFilterPage.isPageLoaded();
        productsFilterPage.filterProductsBySizeAndColor();
        productsFilterPage.validateThatProductsIncludeRedColourAndSSize();
    }
}
