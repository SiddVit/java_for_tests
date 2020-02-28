package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        select(By.name("bday"), contactData.getBday());
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());

        if (creation) {
            if (isElementPresent(By.name("new_group"))) {
                select(By.name("new_group"), contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initModificationContact() {
        click(By.xpath("//td[8]//a[1]//img[1]"));
    }

    public void submitContactFormModification() {
        click(By.name("update"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[2]//input[1]"));
    }

    public void createContact(ContactData contact, boolean creation) {
        fillContactForm(contact, creation);
        submitContactForm();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String firstname = element.getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            ContactData contact = new ContactData(id, firstname, null, null, null, null,
                    null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}