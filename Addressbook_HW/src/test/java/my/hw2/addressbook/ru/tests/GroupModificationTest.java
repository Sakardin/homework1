package my.hw2.addressbook.ru.tests;

import my.hw2.addressbook.ru.Model.GroupData;
import org.testng.annotations.Test;

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
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupMddigication();
        app.getGroupHelper().fillGroupForm(new GroupData("hometest11", "hometest22", "hometest33"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }
}
