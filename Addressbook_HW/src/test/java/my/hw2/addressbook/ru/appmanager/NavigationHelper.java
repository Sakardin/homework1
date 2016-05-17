package my.hw2.addressbook.ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class NavigationHelper extends HelperBase{


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));

    }
}
