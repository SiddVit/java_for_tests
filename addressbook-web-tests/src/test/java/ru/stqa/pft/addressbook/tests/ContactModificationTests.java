package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testsContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoNewContact();
            app.getContactHelper().createContact(new ContactData("Bred", "Pitt",
                    "YouKnowThisGay", "Hollywood", "1", "November", "1990",
                    "Test 1"), true);
            app.getNavigationHelper().gotoHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Marry", "Duck",
                "YouKnowThisGay", "Hollywood", "1", "November", "1990",
                "Test 1");

        int modifIndex = before.size() - 1;
        app.getContactHelper().selectContact(modifIndex);
        app.getContactHelper().initModificationContact(modifIndex);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactFormModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}