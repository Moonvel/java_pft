package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        app.goTo().gotoHomePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("Ivan").withLastName("White").withNickName("Pause").withGroup("123");
        app.contact().createContact(contact, true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);



    }

}
