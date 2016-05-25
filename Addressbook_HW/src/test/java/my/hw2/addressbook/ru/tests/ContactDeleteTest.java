package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete(){
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("testname", "testmiddlename", "testlastname",
                    "testnickname", "testitle", "testcompany", "testaddress", "testhome", "testmobile",
                    "testwork", "testfax", "hometest1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().allertAccept();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals( after.size(), before.size() - 1);

//        before.remove(before.size() - 1);
//        Assert.assertEquals(before, after);

    }
}
