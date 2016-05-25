package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactModificationTest extends TestBase {

    @Test(enabled = false)
    public void testContactModification(){
        if(! app.getContactHelper().isThereAContact()){
            ContactData contactNew = new ContactData("testname", "testmiddlename", "testlastname",
                    "testnickname", "testitle", "testcompany", "testaddress", "testhome", "testmobile",
                    "testwork", "testfax", "hometest1");
            app.getContactHelper().createContact(contactNew);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        ContactData contact = new ContactData("testname1", "testmiddlename1",
                "testlastname1", "testnickname1", "testitle1", "testcompany1",
                "testaddress1", "testhome1", "testmobile1", "testwork1", "testfax1", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContactForm();
        app.getContactHelper().retuntToMainPage();
//        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1 , g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals( before, after);


    }
}
