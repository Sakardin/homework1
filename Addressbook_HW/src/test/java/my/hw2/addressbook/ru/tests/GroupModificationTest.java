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

    @Test(enabled = false)
    public void testGroupModification() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().createGroup(new GroupData("test","test,","rest"));
        }
        List<GroupData> before = app.group().getGrouplist();
        app.group().selectGroup(before.size() - 1);

        app.group().initGroupMddigication();
        GroupData group = new GroupData(before.get(before.size() -1).getId(), "hometest11", "hometest22", "hometest33");
        app.group().fillGroupForm(group);
        app.group().submitGroupModification();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().getGrouplist();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1 , g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals( before, after);


    }
}
