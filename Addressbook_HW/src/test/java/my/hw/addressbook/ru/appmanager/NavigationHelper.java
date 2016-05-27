package my.hw.addressbook.ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class NavigationHelper extends HelperBase{


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }
    public void groupPage(){
        if (isElementPresent(By.tagName("h1"))  && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
            return;
        }else {
            click(By.linkText("groups"));
        }
    }


    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        } else {
            click(By.linkText("home"));
        }

    }


}
