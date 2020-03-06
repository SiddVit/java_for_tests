package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withFirstname("Bred").withLastname("Pitt")
                    .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                    .withByear("1990").withGroup("Test 1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testsContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Bred")
                .withLastname("Pitt").withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1")
                .withBmonth("November").withByear("1990").withGroup("Test 1");

        app.contact().modify(index, contact);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}