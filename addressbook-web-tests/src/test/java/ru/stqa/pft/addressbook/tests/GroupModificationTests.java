package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("Test 1"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        GroupData group = new GroupData()
                .withId(before.get(index).getId())
                .withName("Test Group 1")
                .withHeader("Test Header 2")
                .withFooter("Test Footer 3");


        app.group().modify(group, index);
        app.goTo().returnGroupPage();

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}