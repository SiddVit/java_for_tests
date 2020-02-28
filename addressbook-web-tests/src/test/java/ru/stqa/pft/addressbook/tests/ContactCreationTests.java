package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Bred", "Pitt", "YouKnowThisGay", "Hollywood",
                "November", "1", "1990", "Test 1"), true);
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}