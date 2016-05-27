package my.hw.addressbook.ru.tests;

import my.hw.addressbook.ru.Model.GroupData;
import my.hw.addressbook.ru.Model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test").withFooter("test").withHeader("rest");
        if (!app.group().isThereAGroup()) {
            app.group().create(group);
        }
    }

    @Test

        public void testGroupDelete() {

        Groups before;
        before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));

        Groups after = app.group().all();
        before.remove(deletedGroup);

        assertThat(after, equalTo(before.without(deletedGroup)));

    }
}

