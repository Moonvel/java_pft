package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData("Bob", "White", "Pause","+19123441212"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        app.getLogout();
    }

}
