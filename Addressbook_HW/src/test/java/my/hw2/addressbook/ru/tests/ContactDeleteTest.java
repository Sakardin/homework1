package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.ContactData;
import my.hw2.addressbook.ru.Model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void testPrecondition() {
        if (!app.contact().isThereAContact()) {
            ContactData contact = new ContactData().witFirstName("testname").withMiddleName("testmiddlename").withLastName("testlastname")
                    .withNickName("testnickname").withTitle("testitle").withCompany("testcompany").withAddress("testaddress")
                    .withHomePhone("testhome").withMobilePhone("testmobile").withWorkPhone("testwork").withFax("testfax")
                    .withHomePhone("hometest1");
            app.contact().create(contact);
        }
    }


    @Test
    public void testContactDelete(){

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete();
        assertThat(app.contact().count(), equalTo(before.size() -1 ));
        Contacts after = app.contact().all();
        before.remove(deletedContact);

        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
