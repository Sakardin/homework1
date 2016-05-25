package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeleteTest extends TestBase {


    @Test(enabled = false)
    public void testGroupDelete() {
        app.goTo().groupPage();
        GroupData group = new GroupData("test","test,","rest");
        if (! app.group().isThereAGroup()){
            app.group().createGroup(group);
        }
        List<GroupData> before = app.group().getGrouplist();
        app.group().selectGroup(0);
        app.group().deleteSelectedGroup();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().getGrouplist();

        Assert.assertEquals(after.size(), before.size() - 1);

//        before.remove(before.size() - 1);
//            Assert.assertEquals(before, after);

    }

}