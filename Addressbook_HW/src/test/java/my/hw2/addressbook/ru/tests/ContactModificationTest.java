package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("testname1", "testmiddlename1",
                "testlastname1", "testnickname1", "testitle1", "testcompany1",
                "testaddress1", "testhome1", "testmobile1", "testwork1", "testfax1"));
        app.getContactHelper().updateContactForm();
        app.getContactHelper().retuntToMainPage();

    }
}
