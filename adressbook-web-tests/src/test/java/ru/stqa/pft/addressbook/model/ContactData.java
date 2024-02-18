package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String address;
    private String group;
    private String email;

    public int getId() {
        return id;
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
    public String getMobilePhone() {
        return mobilePhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getGroup() {
        return group;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }
    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }
    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", home='" + homePhone + '\'' +
                ", mobile='" + mobilePhone + '\'' +
                ", work='" + workPhone + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
