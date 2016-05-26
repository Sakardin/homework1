package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {



    @Test(enabled = false)
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData("testname", "testmiddlename", "testlastname",
                "testnickname", "testitle", "testcompany", "testaddress", "testhome", "testmobile",
                "testwork", "testfax", "hometest1");
        app.getContactHelper().fillContactForm(contact,true);
        app.getContactHelper().submitContactForm();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId;
        byId = (c1 , c2) -> Integer.compare(c1.getId() , c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }


}
