package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Bred", "Pitt",
                "YouKnowThisGay", "Hollywood", "November", "1", "1990",
                "Test 1"), true);
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}