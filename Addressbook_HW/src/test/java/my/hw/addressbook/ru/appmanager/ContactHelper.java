package my.hw.addressbook.ru.appmanager;

import my.hw.addressbook.ru.Model.ContactData;
import my.hw.addressbook.ru.Model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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



    public void creation() {
        click(By.linkText("add new"));

    }

    public void selectContact(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
//        click(By.name("selected[]"));
    }

    public void allertAccept() {
        wd.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }


    public void editContactById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href =('edit.php?id=%s')]", id))).click();
//        click(By.xpath("//img[@title=('Edit')]"));
    }
//    public void initContactModificationById(int id){
//        WebElement chekbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
//        WebElement row = chekbox.findElement(By.xpath("./../."));
//        List<WebElement> cells = row.findElements(By.tagName("td"));
//        cells.get(7).findElement(By.tagName("a")).click();
//
//
//    }

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
        creation();
        fillContactForm(data, true);
        submitContactForm();
        gotoHomePage();
    }


    public boolean isThereAContact() {
        if (isElementPresent(By.name("selected[]")))return true;
        else return false;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();

    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
//            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String firstName = cells.get(2).getText();
//            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String lastName = cells.get(1).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData().withId(id).witFirstName(firstName).withLastName(lastName)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));

        }
        return new Contacts(contactCache);
    }

        public ContactData infoFromEditForm(ContactData contact) {
            initContactModificationById(contact.getId());
            String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
            String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
            String home = wd.findElement(By.name("home")).getAttribute("value");
            String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
            String work = wd.findElement(By.name("work")).getAttribute("value");
            wd.navigate().back();
            return new ContactData().withId(contact.getId()).witFirstName(firstName).withLastName(lastName)
                    .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
        }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();


    }

    public void create(ContactData contact) {
        fillContactForm(contact,true);
        submitContactForm();
        contactCache = null;
        gotoHomePage();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectedContact();
        allertAccept();
        contactCache = null;
        gotoHomePage();
    }
    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact, false);
        updateContactForm();
        contactCache = null;
        retuntToMainPage();
    }
}