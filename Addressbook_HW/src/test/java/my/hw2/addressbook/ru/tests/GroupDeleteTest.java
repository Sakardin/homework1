package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeleteTest extends TestBase {


    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().gotoGroupPage();
        GroupData group = new GroupData("test","test,","rest");
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(group);
        }
        List<GroupData> before = app.getGroupHelper().getGrouplist();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGrouplist();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
            Assert.assertEquals(before, after);

    }

}