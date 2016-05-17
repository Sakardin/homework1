package my.hw2.addressbook.ru.appmanager;

import my.hw2.addressbook.ru.Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
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

    public void selectContact() {
        click(By.cssSelector("tbody tr.odd td.center:nth-child(1)"));
    }

    public void allertAccept() {
        wd.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[4]/td[8]/a/img"));
    }

    public void updateContactForm() {
        click(By.name("update"));
    }

    public void retuntToMainPage() {
        click(By.xpath("//div/div[4]/div/i/a"));
    }
}
