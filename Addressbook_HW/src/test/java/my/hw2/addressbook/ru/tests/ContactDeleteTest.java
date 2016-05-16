package my.hw2.addressbook.ru.tests;

import org.testng.annotations.Test;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().allertAccept();

    }
}
