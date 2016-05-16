package my.hw2.addressbook.ru.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {


    @Test
    public void testGroupDelete() {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroup();
        app.returnToGroupPage();

    }

}