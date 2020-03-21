package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @Test
    public void testContactCreationWithPhoto() {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/11346-slider_thumb.jpg");
        ContactData contact = new ContactData().withFirstname("Bred").withLastname("Pitt")
                .withNickname("YouKnowThisGay").withCompany("Hollywood").withBday("1").withBmonth("November")
                .withByear("1990").inGroup(groups.iterator().next()).withHomePhone("+7(111)").withMobilePhone("22-22")
                .withWorkPhone("33 33 33").withEmail1("ss@ma.ru").withEmail2("").withEmail3("logput")
                .withAddress("NY, Paradise st., 10").withPhoto(photo);

        Contacts before = app.db().contacts();

        app.goTo().newContact();

        app.contact().create(contact);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.db().contacts();


        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        verifyContactListInUi();
    }

    @Test(dataProvider = "validContactsFromJSON", enabled = false)
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().all();

        app.goTo().newContact();


        app.contact().create(contact);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();


        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        verifyContactListInUi();
    }
}