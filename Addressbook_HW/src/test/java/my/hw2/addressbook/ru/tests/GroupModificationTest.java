package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test","test,","rest"));
        }
        List<GroupData> before = app.getGroupHelper().getGrouplist();
        app.getGroupHelper().selectGroup(before.size() - 1);

        app.getGroupHelper().initGroupMddigication();
        GroupData group = new GroupData(before.get(before.size() -1).getId(), "hometest11", "hometest22", "hometest33");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGrouplist();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1 , g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals( before, after);


    }
}
