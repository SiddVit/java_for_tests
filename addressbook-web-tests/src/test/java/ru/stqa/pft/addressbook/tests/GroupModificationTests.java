package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test 1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();


        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("Test Group 1")
                .withHeader("Test Header 2")
                .withFooter("Test Footer 3");


        app.goTo().groupPage();
        app.group().modify(group);
        app.goTo().returnGroupPage();

        assertEquals(app.group().count(), before.size());

        Groups after = app.db().groups();

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}