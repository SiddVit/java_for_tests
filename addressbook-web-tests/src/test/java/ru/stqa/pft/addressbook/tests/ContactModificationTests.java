package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testsContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoNewContact();
            app.getContactHelper().createContact(new ContactData("Bred", "Pitt", "YouKnowThisGay", "Hollywood",
                    "November", "1", "1990", "Test 1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initModificationContact();
        app.getContactHelper().fillContactForm(new ContactData("Marry", "Duck", "YouKnowThisGay", "Hollywood",
                "November", "1", "1990", null), false);
        app.getContactHelper().submitContactFormModification();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
