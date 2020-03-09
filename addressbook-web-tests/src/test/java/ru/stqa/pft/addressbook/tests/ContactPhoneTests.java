package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test(enabled = false)
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contac = app.contact().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm();
    }
}
