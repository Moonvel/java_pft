package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailTests extends TestBase{
    @Test
    public void testContactDetails(){
            app.goTo().gotoHomePage();
            ContactData contact = app.contact().all().iterator().next();
            String contactInfoFromDetailForm = app.contact().infoFromDetailForm(contact);
            assertThat(mergeContactString(contact), equalTo(cleaned(contactInfoFromDetailForm)));
        }

    private String mergeContactString(ContactData contact) {
        return Stream.of(contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getAllPhones(), contact.getEmail())
                .filter((s) -> !s.isEmpty()).map(ContactDetailTests::cleaned)
                .collect(Collectors.joining(""));

    }
    public static String cleaned(String contactString) {
        return contactString.replaceAll("\\s", "").replaceAll("[-():HW\n]", "");
    }
    }
