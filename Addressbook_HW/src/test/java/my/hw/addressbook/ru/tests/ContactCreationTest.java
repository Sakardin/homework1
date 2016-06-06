package my.hw.addressbook.ru.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import my.hw.addressbook.ru.Model.ContactData;
import my.hw.addressbook.ru.Model.Contacts;
import my.hw.addressbook.ru.Model.GroupData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }
    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @Test(dataProvider = "validGroupsFromJson")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().all();
        app.contact().creation();
        File photo = new File("src/tests/resources/Fly.jpeg");
//        ContactData contact = new ContactData().witFirstName("testname").withMiddleName("testmiddlename").withLastName("testlastname")
//                .withNickName("testnickname").withTitle("testitle").withCompany("testcompany").withAddress("testaddress")
//                .withHomePhone("testhome").withMobilePhone("testmobile").withWorkPhone("testwork").withFax("testfax")
//                .withHomePhone("hometest1").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}


