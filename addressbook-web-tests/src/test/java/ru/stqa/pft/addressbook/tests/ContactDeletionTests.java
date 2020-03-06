package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
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
    public void testsContactDeletion() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().select(index);
        app.contact().deleteSelectedContact();
        app.contact().closeAlert();
        Thread.sleep(5000);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}