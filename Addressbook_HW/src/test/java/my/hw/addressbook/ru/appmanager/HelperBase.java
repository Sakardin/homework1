package my.hw.addressbook.ru.appmanager;

import org.openqa.selenium.*;

import java.io.File;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd =wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if( text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if(! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }
    public void attach(By locator, File file) {
                if( file != null) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
        }

    public  boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
        return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
