package tests;

import pages.CartPage;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;


import java.util.List;

public class CartTest extends BaseTest {

    @Test(testName = "Add item to cart flow")
    public void addItemToCartFlow(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        cartPage.addItemToCart("rochii");
        cartPage.validateThatTheItemWasAddedProperlyToCart();
    }

    @Test
    public void addItemToCartAndRefreshPageFlow(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        cartPage.addItemToCartAndRefreshPage("rochii");
        cartPage.validateThatTheItemIsStillInCartAfterPageRefresh();
    }

    @Test
    public void addMultipleItemsToCartFlow(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        PropertyUtility propertyUtility = new PropertyUtility("CartItems");
        List<String> productNames = propertyUtility.getPropertiesAsList("products");
        cartPage.addMultipleItemsToCart(productNames);
        cartPage.validateThatTheMultipleItemsWereAddedProperlyToCart(productNames);
    }

    @Test
    public void addMultipleItemsToCartAndRemoveThemFlow(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        PropertyUtility propertyUtility = new PropertyUtility("CartItems");
        List<String> productNames = propertyUtility.getPropertiesAsList("products");
        cartPage.addMultipleItemsToCartAndRemoveThem(productNames);
        cartPage.validateThatTheCartIsEmpty(productNames);
    }
}