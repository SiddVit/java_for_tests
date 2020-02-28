package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testsContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoNewContact();
            app.getContactHelper().createContact(new ContactData("Bred", "Pitt", "YouKnowThisGay", "Hollywood",
                    "November", "1", "1990", "Test 1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
