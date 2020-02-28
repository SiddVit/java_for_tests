package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testsContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoNewContact();
            app.getContactHelper().createContact(new ContactData("Bred", "Pitt",
                    "YouKnowThisGay", "Hollywood", "November", "1", "1990",
                    "Test 1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}