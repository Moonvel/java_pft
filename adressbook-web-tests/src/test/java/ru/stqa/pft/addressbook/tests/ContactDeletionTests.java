package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @Test
    public void contactDeletionTests(){
        app.goTo().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                    "Ivan", "White", "Pause","+19123441212","+1123132","+1231233", "123"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().delete(deletedContact);
        app.getContactHelper().closeAlertWindow();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}
