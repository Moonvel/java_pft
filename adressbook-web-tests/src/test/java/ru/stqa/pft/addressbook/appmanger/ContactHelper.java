package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    NavigationHelper navigationHelper = new NavigationHelper(wd);
    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("home"), contactData.getMobile());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());


        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void closeAlertWindow() {
        wd.switchTo().alert().accept();
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null,null);
            contacts.add(contact);

        }
    return contacts;
    }
    public void initContactModification(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

   //доделать
     public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData(
                contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getNickName(),
                contact.getHome(), contact.getMobile(), contact.getWork(), contact.getGroup());
    }
}