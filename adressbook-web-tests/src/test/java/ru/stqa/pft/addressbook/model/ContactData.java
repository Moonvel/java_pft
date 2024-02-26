package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    @Expose
    private String firstName;
    @Column(name = "lastname")
    @Expose
    private String lastName;
    private String nickName;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    @Expose
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Type(type = "text")
    private String address;
    @Transient
    private String group;
    @Type(type = "text")
    private String email;
    @Column(name = "photo")
    @Type(type = "text")
    private String avatar;

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
    public File getAvatar() {
        return new File(avatar);
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
    public ContactData withAvatar(File avatar) {
        this.avatar = avatar.getPath();
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
