package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Objects;

public class ContactHelper extends HelperBase {
    public Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactHelper that = (ContactHelper) o;

        return Objects.equals(contactCache, that.contactCache);
    }

    @Override
    public int hashCode() {
        return contactCache != null ? contactCache.hashCode() : 0;
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        select(By.name("bday"), contactData.getBday());
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertEquals(contactData.getGroups().size(), 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void modification(int index) {
        wd.findElement(By.cssSelector("[href^='edit.php?id=" + index + "']")).click();
    }

    public void submitFormModification() {
        click(By.name("update"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[2]//input[1]"));
    }

    public void create(ContactData contact, boolean creation) {
        fillForm(contact, creation);
        submitContactForm();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElements(By.tagName("td")).get(2).getText();
            String lastname = element.findElements(By.tagName("td")).get(1).getText();
            String allphones = element.findElements(By.tagName("td")).get(5).getText();
            String allemail = element.findElements(By.tagName("td")).get(4).getText();
            String alladdress = element.findElements(By.tagName("td")).get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allphones).withAllEmail(allemail).withAddress(alladdress));
        }
        return new Contacts(contactCache);
    }

    public void create(ContactData contact) {
        fillForm(contact, true);
        submitContactForm();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        modification(contact.getId());
        fillForm(contact, false);
        submitFormModification();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        closeAlert();
        contactCache = null;
    }

    public ContactData infoPhoneFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactData infoEmailFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public ContactData infoAddressFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address);
    }

    public void submitContactToGroup() {
        wd.findElement(By.name("add")).click();
    }

    public void selectGroupFilterByName(GroupData group) {
        String groupId = String.valueOf(group.getId());
        new Select(wd.findElement(By.name("group"))).selectByValue(groupId);
    }

    public void submitContactDeleteFromGroup() {
        wd.findElement(By.name("remove")).click();
    }

    public void selectGroup(ContactData user, GroupData group){
        selectContactById(user.getId());
        String groupId = String.valueOf(group.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(groupId);
        submitContactToGroup();
        contactCache = null;
    }

    public void allGroupsOnUserPage() {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    }
}