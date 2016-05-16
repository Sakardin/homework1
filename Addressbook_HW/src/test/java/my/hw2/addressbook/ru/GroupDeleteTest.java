package my.hw2.addressbook.ru;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {


    @Test
    public void testGroupDelete() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();

    }

}