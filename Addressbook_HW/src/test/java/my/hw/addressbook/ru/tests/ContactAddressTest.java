package my.hw.addressbook.ru.tests;

import my.hw.addressbook.ru.Model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dmitry on 27.05.2016.
 */
public class ContactAddressTest extends TestBase {

    @Test
    public void testAddress() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));

    }
}
