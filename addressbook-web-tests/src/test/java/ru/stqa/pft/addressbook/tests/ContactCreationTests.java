package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        ContactData contact = new ContactData().withFirstname("Bred").withLastname("Pitt")
                .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                .withByear("1990").withGroup("Test 1");

        List<ContactData> before = app.contact().list();

        app.goTo().newContact();
        app.contact().create(contact);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();

        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}