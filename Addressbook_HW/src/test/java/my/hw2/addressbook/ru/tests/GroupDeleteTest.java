package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {


    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test","test,","rest"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();

    }

}