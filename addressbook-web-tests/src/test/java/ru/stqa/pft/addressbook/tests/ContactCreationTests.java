package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Bred", "Pitt", "YouKnowThisGay", "Hollywood",
                "November", "1", "1990"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().gotoHomePage();
    }
}