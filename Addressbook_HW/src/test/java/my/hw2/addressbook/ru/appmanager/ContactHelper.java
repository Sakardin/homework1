package my.hw2.addressbook.ru.appmanager;

import my.hw2.addressbook.ru.Model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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

    public void fillContactForm(ContactData contactData, boolean creation) {
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

        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }



    public void initContactCreation() {
        click(By.linkText("add new"));

    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void allertAccept() {
        wd.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void editContact() {
        click(By.xpath("//img[@title=('Edit')]"));
    }

    public void updateContactForm() {
        click(By.name("update"));
    }

    public void retuntToMainPage() {
        click(By.xpath("//div/div[4]/div/i/a"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

    public void createContact(ContactData data) {
        initContactCreation();
        fillContactForm(data, true);
        submitContactForm();
        gotoHomePage();
    }


    public boolean isThereAContact() {
        if (isElementPresent(By.name("selected[]")))return true;
        else return false;
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData group = new ContactData(id, firstName, null, lastName, null, null, null, null, null, null, null, null, null);
            contacts.add(group);

        }
        return contacts;
    }
}
