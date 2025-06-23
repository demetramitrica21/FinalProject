package tests;

import pages.CartPage;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;

import java.util.Arrays;
import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        cartPage.addItemToCart("rochii");
        cartPage.validateThatTheItemWasAddedProperlyToCart();
    }

    @Test
    public void refreshPageAndCheckCart(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        cartPage.refreshPageAndCheckCart("rochii");
        cartPage.validateThatTheItemIsStillInCartAfterPageRefresh();
    }

    @Test
    public void addMultipleItemsToCart(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        PropertyUtility propertyUtility = new PropertyUtility("CartItems");
        List<String> productNames = propertyUtility.getPropertiesAsList("products");
        cartPage.addMultipleItemsToCart(productNames);
        cartPage.validateThatTheMultipleItemsWereAddedProperlyToCart(productNames);
    }

    @Test
    public void addMultipleItemsToCartAndRemoveThem(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        PropertyUtility propertyUtility = new PropertyUtility("CartItems");
        List<String> productNames = propertyUtility.getPropertiesAsList("products");
        cartPage.addMultipleItemsToCartAndRemoveThem(productNames);
        cartPage.validateThatTheCartIsEmpty(productNames);
    }
}