package my.hw2.addressbook.ru.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {


    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();

    }

}