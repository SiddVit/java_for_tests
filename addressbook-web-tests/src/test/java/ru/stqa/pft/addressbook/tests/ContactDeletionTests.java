package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testsContactDeletion() throws InterruptedException {
        ContactData contact = new ContactData("Bred", "Pitt",
                "YouKnowThisGay", "Hollywood", "1", "November", "1990",
                "Test 1");

        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoNewContact();
            app.getContactHelper().createContact(contact, true);
            app.getNavigationHelper().gotoHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();
        Thread.sleep(5000);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}