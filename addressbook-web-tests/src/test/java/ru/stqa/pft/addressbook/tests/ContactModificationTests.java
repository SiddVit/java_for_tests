package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testsContactModification() {
        app.getContactHelper().initModificationContact();
        app.getContactHelper().fillContactForm(new ContactData("Marry", "Duck", "YouKnowThisGay", "Hollywood",
                "November", "1", "1990"));
        app.getContactHelper().submitContactFormModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
