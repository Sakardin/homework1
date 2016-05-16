package my.hw2.addressbook.ru.appmanager;

import my.hw2.addressbook.ru.Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(FirefoxDriver wd) {
       super(wd);
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));

    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
    }



    public void initContactCreation() {
        click(By.linkText("add new"));

    }
}
