package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactToGroupTests extends TestBase {

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
    public void testContactDeleteFromGroup() {
        ContactData userAfter = null;
        ContactData userSelect;
        GroupData groupSelect = null;

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        app.goTo().homePage();
        userSelect = contacts.iterator().next();

        for (ContactData currentUser : contacts){
            Groups currentGroup = currentUser.getGroups();
            if (currentGroup.size() > 0){
                userSelect = currentUser;
                groupSelect = currentUser.getGroups().iterator().next();
                break;
            }
        }

        if (userSelect.getGroups().size() == 0){
            groupSelect = groups.iterator().next();
            app.contact().selectGroup(userSelect, groupSelect);
        }

        app.contact().selectGroupFilterByName(groupSelect);
        app.contact().selectContactById(userSelect.getId());
        app.contact().submitContactDeleteFromGroup();
        app.goTo().mainPage();

        Contacts usersAllAfter = app.db().contacts();
        for (ContactData userChoiceAfter : usersAllAfter){
            if (userChoiceAfter.getId() == userSelect.getId()){
                userAfter = userChoiceAfter;
            }
        }

        assertThat(userSelect.getGroups(), equalTo(userAfter.getGroups().withAdded(groupSelect)));
    }
}
