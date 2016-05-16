package my.hw2.addressbook.ru;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {



    @Test
    public void testContactCreation() {

        initContactCreation();
        fillContactForm(new ContactData("testname", "testmiddlename", "testlastname", "testnickname", "testitle", "testcompany", "testaddress", "testhome", "testmobile", "testwork", "testfax"));
        submitContactForm();
    }


}
