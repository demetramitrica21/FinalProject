package tests;

import org.testng.annotations.Test;
import pages.ProductsFilterPage;
import propertyUtility.PropertyUtility;

import java.util.Map;
import java.util.Objects;

public class ProductsFilterTest extends BaseTest {

    @Test
    public void filterProductsByColorAndSize(){
        ProductsFilterPage productsFilterPage= new ProductsFilterPage(driver);
        productsFilterPage.isPageLoaded();
        productsFilterPage.filterProductsBySizeAndColor();
        productsFilterPage.validateThatProductsContainRedColourAndSSize();
    }
}
