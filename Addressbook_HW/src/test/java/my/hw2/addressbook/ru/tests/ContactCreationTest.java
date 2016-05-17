package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {



    @Test
    public void testContactCreation() {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("testname", "testmiddlename", "testlastname",
                "testnickname", "testitle", "testcompany", "testaddress", "testhome", "testmobile",
                "testwork", "testfax", "hometest1"),true);
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().gotoHomePage();
    }


}
