package my.hw.addressbook.ru.tests;

import my.hw.addressbook.ru.Model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by user on 5/27/2016.
 */
public class ContactPhoneTest extends TestBase{

    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
        assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));
        assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));

    }
}
