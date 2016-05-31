package my.hw.addressbook.ru.tests;

import my.hw.addressbook.ru.Model.ContactData;
import my.hw.addressbook.ru.Model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.contact().creation();
        File photo = new File("src/tests/resources/Fly.jpeg");
        ContactData contact = new ContactData().witFirstName("testname").withMiddleName("testmiddlename").withLastName("testlastname")
                .withNickName("testnickname").withTitle("testitle").withCompany("testcompany").withAddress("testaddress")
                .withHomePhone("testhome").withMobilePhone("testmobile").withWorkPhone("testwork").withFax("testfax")
                .withHomePhone("hometest1").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}


