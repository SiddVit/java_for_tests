package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    File photo = new File("src/test/resources/11346-slider_thumb.jpg");

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withFirstname("Bred").withLastname("Pitt")
                    .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                    .withByear("1990").withGroup("Test 1").withHomePhone("+7(111)").withMobilePhone("22-22")
                    .withWorkPhone("33 33 33").withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput")
                    .withAddress("NY, Paradise st., 10").withPhoto(photo), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testsContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Maggie")
                .withLastname("Duck").withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1")
                .withBmonth("November").withByear("1990").withGroup("Test 1")
                .withHomePhone("+7(111)").withMobilePhone("22-22").withWorkPhone("33 33 33")
                .withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput").withAddress("NY, Paradise st., 10")
                .withPhoto(photo);

        app.contact().modify(contact);
        app.goTo().homePage();

        assertThat(app.group().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}