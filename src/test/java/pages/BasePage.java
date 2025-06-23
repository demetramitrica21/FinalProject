package pages;

import helpers.ElementMethods;
import org.openqa.selenium.WebDriver;
import propertyUtility.PropertyUtility;

public abstract class BasePage {
    public WebDriver driver;
    public ElementMethods elementMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        elementMethods = new ElementMethods(driver);
    }

    public abstract void isPageLoaded();
}
