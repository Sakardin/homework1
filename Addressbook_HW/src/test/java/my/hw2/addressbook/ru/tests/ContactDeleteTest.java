package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import org.testng.annotations.Test;

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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().allertAccept();

    }
}
