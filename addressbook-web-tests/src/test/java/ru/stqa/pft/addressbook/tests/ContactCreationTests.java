package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        ContactData contact = new ContactData("Bred", "Pitt",
                "YouKnowThisGay", "Hollywood", "1", "November", "1990",
                "Test 1");

        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().newContact();
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactForm();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}