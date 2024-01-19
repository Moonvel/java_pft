package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() throws Exception {
        initGroupCreation();
        fillGroupForm(new GroupData("123", "123", "123"));
        submitGroupCreation();
        returnToGroupPage();
        getLogout();
    }

}
