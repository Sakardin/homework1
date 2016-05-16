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
        app.getContactHelper().fillContactForm(new ContactData("testname", "testmiddlename", "testlastname", "testnickname", "testitle", "testcompany", "testaddress", "testhome", "testmobile", "testwork", "testfax"));
        app.getContactHelper().updateContactForm();
        app.getContactHelper().retuntToMainPage();

    }
}