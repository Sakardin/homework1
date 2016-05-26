package my.hw2.addressbook.ru.appmanager;

import my.hw2.addressbook.ru.Model.GroupData;
import my.hw2.addressbook.ru.Model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 16.05.2016.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
//        click(By.name("selected[]"));
    }

    public void initGroupMddigication() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }



    public boolean isThereAGroup() {
        if (isElementPresent(By.name("selected[]"))) return true;
        else return false;
           }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();

    }

//    public List<GroupData> getGrouplist() {
//        List<GroupData> groups = new ArrayList<GroupData>();
//        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
//        for (WebElement element : elements){
//            String name = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            GroupData group = new GroupData().withId(id).withN name, null, null);
//            groups.add(group);
//        }
//        return  groups;
//    }

    private Groups groupCache = null;


    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }

        return new Groups(groupCache);
    }

        public void create(GroupData group) {
            initGroupCreation();
            fillGroupForm(group);
            submitGroupCreation();
            returnToGroupPage();

        }

        public void delete(GroupData group) {
            selectGroup(group.getId());
            deleteSelectedGroup();
            returnToGroupPage();

        }

        public void modify(GroupData group) {
            selectGroup(group.getId());
            initGroupMddigication();
            fillGroupForm(group);
            submitGroupModification();
            returnToGroupPage();
        }


}
