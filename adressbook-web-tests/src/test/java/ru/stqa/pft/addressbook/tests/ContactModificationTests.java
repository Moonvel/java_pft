package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void contactModificationTests(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Den", "Black", "Stop","+212123133"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        app.getLogout();
    }
}
