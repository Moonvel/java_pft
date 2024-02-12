package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int Id;
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String mobile;
    private final String group;



    public ContactData(int Id, String firstName, String lastName, String nickName, String mobile, String group) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.mobile = mobile;
        this.group = group;
    }

    public ContactData(String firstName, String lastName, String nickName, String mobile, String group) {
        this.Id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.mobile = mobile;
        this.group = group;
    }

    public int getId() {
        return Id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGroup() {
        return group;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
