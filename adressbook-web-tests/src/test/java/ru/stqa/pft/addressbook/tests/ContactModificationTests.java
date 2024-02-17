package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {
    @Test
    public void contactModificationTests(){
        app.goTo().gotoHomePage();
        if (!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstName("Ivan")
                    .withLastName("White").withNickName("Pause").withGroup("123"), true);
        }
        Set<ContactData> before = app.contact().all();

        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Peter").withLastName("Black").withNickName("Stop").withGroup("123");
        app.contact().modify(contact);
        app.goTo().gotoHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
