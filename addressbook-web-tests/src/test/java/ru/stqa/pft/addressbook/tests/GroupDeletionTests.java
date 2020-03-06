package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("Test 1"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();

        app.group().delete(deletedGroup);
        app.goTo().returnGroupPage();

        Groups after = app.group().all();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}