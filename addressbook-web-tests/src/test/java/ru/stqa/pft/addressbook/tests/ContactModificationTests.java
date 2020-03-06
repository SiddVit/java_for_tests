package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData("Bred", "Pitt",
                    "YouKnowThisGay", "Hollywood", "1", "November", "1990",
                    "Test 1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testsContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        ContactData contact = new ContactData(before.get(index).getId(), "Marry", "Duck",
                "YouKnowThisGay", "Hollywood", "1", "November", "1990",
                "Test 1");

        app.contact().modify(index, contact);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}