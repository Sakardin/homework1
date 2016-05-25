package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGrouplist();
        app.getGroupHelper().initGroupCreation();
        GroupData group = new GroupData("hometest1", "hometest2", "hometest3");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGrouplist();
        Assert.assertEquals(after.size(), before.size() + 1);


        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before,after);

    }

}
