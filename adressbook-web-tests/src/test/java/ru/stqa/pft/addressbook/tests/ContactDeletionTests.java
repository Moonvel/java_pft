package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase{
    @Test
    public void contactDeletionTests(){
        app.goTo().gotoHomePage();
        if (!app.contact().isThereAContact()) {
            app.contact().createContact(
                    new ContactData().withFirstName("Ivan")
                            .withLastName("White").withNickName("Pause").withGroup("123"), true);
        }
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);

    }
}
