package interactives;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by amruta.patel on 28/08/2017.
 */
public class PageBase extends DriverFactory {

    private int defaultSecsToWait = 40;

    private boolean implicitWait(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try {
          return true;
        } catch (TimeoutException e) {
           logElementNotFound(e);
           return false;
        }
    }

    protected WebElement waitForElement(WebElement element) {
       return waitForElement(element, defaultSecsToWait);
    }
    protected WebElement waitForElement(WebElement element, int secsToWait) {
        return (new WebDriverWait(driver, secsToWait)).until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForButton(WebElement element){
        return waitForButton(element, defaultSecsToWait);
    }
    protected WebElement waitForButton(WebElement element, int secsToWait) {
        return (new WebDriverWait(driver, secsToWait))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean clickButton(WebElement buttonElement){
        return clickButton(buttonElement,defaultSecsToWait);
    }
    protected boolean clickButton(WebElement buttonElement, int secsToWait) {
        try {
            WebElement element = waitForButton(buttonElement, secsToWait);
            element.click();
        } catch (TimeoutException ex) {
            logElementNotFound(ex);
            return false;
        }
        return true;
    }

    protected boolean clickElement(WebElement element){
        return clickElement(element, defaultSecsToWait);
    }
    protected boolean clickElement(WebElement element, int secsToWait) {
        try {
            WebElement webelement = waitForElement(element, secsToWait);
            webelement.click();
            implicitWait();
        } catch (TimeoutException ex) {
            logElementNotFound(ex);
            return false;
        }
        return true;
    }

    protected boolean clearElementValue(WebElement elementToClear){
        return clearElementValue(elementToClear,defaultSecsToWait);
    }
    protected boolean clearElementValue(WebElement elementToClear, int secsToWait){
        try {
            WebElement element = waitForElement(elementToClear, secsToWait);
            element.clear();
            implicitWait();
        } catch (TimeoutException ex) {
            logElementNotFound(ex);
            return false;
        }
        return true;
    }
    protected boolean sendKeysToElement(WebElement element, String keysToSend) {
        return sendKeysToElement(element,defaultSecsToWait,keysToSend);
    }
    protected boolean sendKeysToElement(WebElement element,int secsToWait, String keysToSend) {
        try {
//            WebElement sendKeysToEle = waitForElement(element, secsToWait);
//            sendKeysToEle.sendKeys(keysToSend);
            element.sendKeys(keysToSend);
            implicitWait();
            implicitWait();
        } catch (TimeoutException ex) {
            logElementNotFound(ex);
            return false;
        }
        return true;
    }
    protected boolean findElement(WebElement element){
        return findElement(element,defaultSecsToWait);
    }
    protected boolean findElement(WebElement element, int secsToWait) {
        try {
            waitForElement(element, secsToWait);
        } catch (TimeoutException ex) {
            logElementNotFound(ex);
            return false;
        }
        return true;
    }

    protected void hoverAndClick(WebElement hoverTo){
        try {
            Actions action = new Actions(driver);
            action.moveToElement(hoverTo).click(hoverTo).build().perform();
        }catch (ElementNotFoundException ex){
            logElementNotFound(ex);
        }
    }

    protected void hover(WebElement hoverTo) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(hoverTo).perform();
        }catch (ElementNotFoundException ex){
            logElementNotFound(ex);
        }
    }

    public Boolean verifyAction(WebElement element, String elementText){
        waitForElement(element,40);
        if(getElementText(element).equals(elementText))
            return true;
        return false;
    }

    public String getElementText(WebElement element){
        if (element.isDisplayed()) {
            return element.getText();
        }
        return null;
    }


    protected void clickEscapeKey(){
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ESCAPE).build().perform();
        }catch (ElementNotFoundException ex){
            logElementNotFound(ex);
        }
    }

    protected  void clickEnterKey() {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ENTER).build().perform();
        }catch (ElementNotFoundException ex){
            logElementNotFound(ex);
        }
    }

    protected WebElement getWebElement(By fieldIdentifier){
        return driver.findElement(fieldIdentifier);
    }

    //unfortunately some fields in WORKDAY wont work untill there is an explicit wait after an action is performed on them
    protected void explicitWait(int waitforMilliSecs){
        try {
            sleep(waitforMilliSecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // When an element is not found sets two levels of logging and sets found = false
    private void logElementNotFound(Exception ex) {
        Log.info ("Element was not returned in a timely manner");
        Log.debug("Looking for a button but got " + ex.getMessage());
    }
}
