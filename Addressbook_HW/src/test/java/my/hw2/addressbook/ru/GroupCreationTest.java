package my.hw2.addressbook.ru;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("hometest1", "hometest2", "hometest3"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
