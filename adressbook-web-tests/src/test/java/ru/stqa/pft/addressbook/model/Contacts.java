package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private final Set<ContactData> delegate;



    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }
    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<>(contacts.delegate);
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<>(contacts);
    }
    public Contacts() {
        this.delegate = new HashSet<>();
    }
    
    public Contacts withAdded(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }
    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
