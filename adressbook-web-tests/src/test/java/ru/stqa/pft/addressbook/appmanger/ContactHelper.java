package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
    NavigationHelper navigationHelper = new NavigationHelper(wd);
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void submitContactCreation() {
        click(By.name("submit"));
    }
    public void selectContact(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }
    public void create(ContactData contact, boolean b) {
        navigationHelper.gotoAddNewPage();
        fillContactForm(contact, b);
        submitContactCreation();
        navigationHelper.gotoHomePage();
    }
    public void delete(ContactData contact) {
        selectContact(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        closeAlertWindow();
        navigationHelper.gotoHomePage();
    }
    public void modify(ContactData contact){
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("home"), contactData.getMobilePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());


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
    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).
                    withAllPhones(allPhones).withAddress(address).withEmail(email);
            contacts.add(contact);
        }
        return contacts;
    }
    public void initContactModificationById(int id) {
        /*WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();*/
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
   //доделать
     public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName)
                .withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email);

    }
}