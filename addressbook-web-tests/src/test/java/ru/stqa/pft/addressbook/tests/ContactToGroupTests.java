package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTests extends TestBase {
    private ContactData targetContact;
    private GroupData targetGroup;

    @BeforeClass
    public void ensurePreconditions() {
        targetContact = new ContactData().withFirstname("Bred").withLastname("Pitt")
                .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                .withByear("1990").withHomePhone("+7(111)").withMobilePhone("22-22")
                .withWorkPhone("33 33 33").withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput")
                .withAddress("NY, Paradise st., 10")
                .withPhoto(new File("src/test/resources/11346-slider_thumb.jpg"));
        targetGroup = new GroupData().withName(String.format("TestGroup %s", System.currentTimeMillis()));

        Contacts contactSet = app.db().contacts();
        Groups groupSet = app.db().groups();

        for (ContactData contact : contactSet) {
            for (GroupData group : groupSet) {

                if (groupSet
                        .stream()
                        .filter(g -> g.getName().equals(group.getName()))
                        .collect(Collectors.toList())
                        .size() > 1
                ) {
                    continue;
                }

                if (!contact.getGroups().contains(group)) {
                    targetContact = contact;
                    targetGroup = group;
                    return;
                }
            }
        }

        app.goTo().newContact();
        app.contact().create(targetContact, true);
        app.goTo().homePage();
        app.goTo().groupPage();
        app.group().create(targetGroup);

        contactSet = app.db().contacts();
        groupSet = app.db().groups();

        targetContact.withId(
                contactSet.stream().mapToInt((c) -> c.getId()).max().getAsInt()
        );

        targetGroup.withId(
                groupSet.stream().mapToInt((g) -> g.getId()).max().getAsInt()
        );

    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().mainPage();
        app.contact().selectContactById(targetContact.getId());
        app.contact().choiceGroup(targetGroup.getName());
        app.contact().submitContactToGroup();
        app.goTo().mainPage();

        ContactData contactFromDb = app.db().getContact(targetContact.getId());

        assertThat(contactFromDb.getGroups(), hasItem(targetGroup));
    }

    @Test(dependsOnMethods = "testAddContactToGroup")
    public void testContactDeleteFromGroup() {
        app.goTo().mainPage();
        app.contact().selectGroupFilterByName(targetGroup.getName());
        app.contact().selectContactById(targetContact.getId());
        app.contact().submitContactDeleteFromGroup();
        app.goTo().mainPage();

        ContactData contactFromDb = app.db().getContact(targetContact.getId());

        assertThat(contactFromDb.getGroups(), not(hasItem(targetGroup)));
    }
}
