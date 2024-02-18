package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().isEmpty()) {
            app.contact().create(new ContactData().withFirstName("Ivan")
                    .withLastName("White").withNickName("Pause").withGroup("123"), true);
        }
    }
    @Test
    public void contactModificationTests(){
        app.goTo().gotoHomePage();

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Peter").withLastName("Black").withNickName("Stop").withGroup("123");
        app.contact().modify(contact);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
