package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import my.hw2.addressbook.ru.Model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            GroupData group = new GroupData().withName("test").withFooter("test").withHeader("rest");
        }

    }

    @Test
    public void testGroupModification() {

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.group().all();


        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals( before, after);
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


    }


}
