package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class ElementMethods {

    public WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public void scrollPageDown(String scrollValue) {
        js.executeScript("window.scrollBy(0," + scrollValue + ")");
    }

    public WebElement getElement(By locator) {
     waitForElement(locator);
        return driver.findElement(locator);
    }

    public String getTextFromElement(By locator){
        return getElement(locator).getText();
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void closePopUpBox(By locator) {
        try {
            WebElement closePopUpBox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (closePopUpBox.isDisplayed()) {
                closePopUpBox.click();
            }
        } catch (Exception e) {
            System.out.println("Close popUp not found. Skipped!");
        }
    }

    public void clickElement(By locator) {
        waitForElement(locator);
        getElement(locator).click();
    }

    public void clickElement(WebElement element) {
        waitForElement(element);
        element.click();
    }

    public void fillElement(By locator, String textValue){
        waitForElement(locator);
        getElement(locator).clear();
        getElement(locator).sendKeys(textValue);
    }

    public void fillElement(WebElement element, String textValue){
        waitForElement(element);
        element.clear();
        element.sendKeys(textValue);
    }

    public void fillElement(By locator, Keys keyValue){
        waitForElement(locator);
        getElement(locator).sendKeys(keyValue);
    }

    public void fillElement(WebElement element, Keys keyValue){
        waitForElement(element);
        element.sendKeys(keyValue);
    }

    public void acceptCookiesPolicy(By locator){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(locator));
            if (acceptCookies.isDisplayed()) {
                acceptCookies.click();
            }
        } catch (Exception e) {
        }
    }

    public Boolean isLocatorDisplayed(By locator){
        try {
            boolean isDisplayed = driver.findElement(locator).isDisplayed();
            System.out.println(driver.findElement(locator));
            System.out.println(isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickElementByTextIgnoreCase(By locator, String textToMatch) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            String txt = element.getText();
            if (element.getText().toLowerCase().startsWith(textToMatch.toLowerCase())) {
                element.click();
                return true;
            }
        }
        return false;
    }

    public void setWait(Long miliSeconds){
        try {
            wait(miliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSleep(Long miliSeconds){
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
        }
    }
}
