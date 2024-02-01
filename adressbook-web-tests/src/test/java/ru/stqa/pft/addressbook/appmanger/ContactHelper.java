package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    NavigationHelper navigationHelper = new NavigationHelper(wd);
    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }
    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("mobile"), contactData.getMobile());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void closeAlertWindow() {
        wd.switchTo().alert().accept();
    }
    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact, boolean b) {
        navigationHelper.gotoAddNewPage();
        fillContactForm(contact, b);
        submitContactCreation();
        navigationHelper.gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));

    }
}