package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("123", "123", "123"));
        app.submitGroupCreation();
        app.returnToGroupPage();
        app.getLogout();
    }

}
