package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

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
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoPhoneFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
                .filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
}