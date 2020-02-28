package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        GroupData group = new GroupData("Test 1", null, null);

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}