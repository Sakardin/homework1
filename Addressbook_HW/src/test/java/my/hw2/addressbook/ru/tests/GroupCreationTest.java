package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("hometest1", "hometest2", "hometest3"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

}
