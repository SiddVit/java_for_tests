package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        ContactData contact = new ContactData().withFirstname("Bred").withLastname("Pitt")
                .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                .withByear("1990").withGroup("Test 1");

        Set<ContactData> before = app.contact().all();

        app.goTo().newContact();
        app.contact().create(contact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}