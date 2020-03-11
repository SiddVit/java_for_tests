package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withFirstname("Bred").withLastname("Pitt")
                    .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                    .withByear("1990").withGroup("Test 1").withHomePhone("+7(111)").withMobilePhone("22-22")
                    .withWorkPhone("33 33 33").withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput")
                    .withAddress("NY, Paradise st., 10"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactsAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactAddressInfoFromEditForm = app.contact().infoAddressFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactAddressInfoFromEditForm.getAddress()));
    }
}