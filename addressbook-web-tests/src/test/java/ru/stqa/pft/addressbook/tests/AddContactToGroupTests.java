package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    ContactData targetContact = new ContactData().withFirstname("Bred").withLastname("Pitt")
            .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
            .withByear("1990").withHomePhone("+7(111)").withMobilePhone("22-22")
            .withWorkPhone("33 33 33").withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput")
            .withAddress("NY, Paradise st., 10")
            .withPhoto(new File("src/test/resources/11346-slider_thumb.jpg"));
    GroupData targetGroup = new GroupData().withName(String.format("TestGroup %s", System.currentTimeMillis()));

    @BeforeClass
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(targetGroup);
        }

        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.goTo().newContact();
            app.contact().create(targetContact.inGroup(groups.iterator().next()), true);
            app.goTo().mainPage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts usersAll = app.db().contacts();
        Groups groupsAll = app.db().groups();

        ContactData userSelect = null;
        GroupData groupSelect = null;
        ContactData userAfter = null;

        for (ContactData currentUser : usersAll){
            Groups groupsOfSelectedUser = currentUser.getGroups();
            if(groupsOfSelectedUser.size() != groupsAll.size()){
                groupsAll.removeAll(groupsOfSelectedUser);
                groupSelect = groupsAll.iterator().next();
                userSelect = currentUser;
                break;
            }
        }
        if (groupSelect == null) {
            ContactData user = targetContact;
            app.goTo().newContact();
            app.contact().create(user, true);
            app.goTo().homePage();
            Contacts userA = app.db().contacts();
            user.withId(userA.stream().mapToInt((g) -> (g).getId()).max().getAsInt());
            userSelect = user;
            groupSelect = groupsAll.iterator().next();
        }

        app.goTo().homePage();
        app.contact().allGroupsOnUserPage();
        app.contact().selectGroup(userSelect, groupSelect);
        app.goTo().mainPage();

        Contacts usersAllAfter = app.db().contacts();
        for (ContactData currentUserAfter : usersAllAfter){
            if (currentUserAfter.getId() == userSelect.getId()){
                userAfter = currentUserAfter;
            }
        }

        assertThat(userSelect.getGroups(), equalTo(userAfter.getGroups().without(groupSelect)));
    }
}
