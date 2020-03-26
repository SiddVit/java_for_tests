package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withFirstname("Bred").withLastname("Pitt")
                    .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                    .withByear("1990").withHomePhone("+7(111)").withMobilePhone("22-22")
                    .withWorkPhone("33 33 33").withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput")
                    .withAddress("NY, Paradise st., 10"), true);
            app.goTo().homePage();
        }

        if (app.db().groupsWithSpecialName().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("TestGroup1"));
            app.goTo().returnGroupPage();
            app.goTo().mainPage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        InGroupContacts before = app.db().contactsGroups();
        ContactData contact = contacts.iterator().next();
        int contactId = contact.getId();
        GroupData group = groups.iterator().next();
        int groupId = group.getId();
        String groupName = group.getName();

        InGroupContactsData contactsGroup = new InGroupContactsData().withContactId(contactId).withGroupId(groupId);

        app.contact().selectContactById(contactId);
        app.contact().addToGroup(groupName);

        InGroupContacts after = app.db().contactsGroups();

        assertThat(after, equalTo(before.withAdded(contactsGroup)));
    }
}
