package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
       click(By.linkText("groups"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }

    public void gotoNewContact() {
        click(By.linkText("add new"));
    }
}
