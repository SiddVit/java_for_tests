package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "nickname")
    private String nickname;
    @Expose
    @Column(name = "company")
    private String company;
    @Expose
    @Column(name = "bday", columnDefinition = "TINYINT")
    private String bday;
    @Expose
    @Column(name = "bmonth")
    private String bmonth;
    @Expose
    @Column(name = "byear")
    private String byear;
    @Expose
    @Transient
    private String group;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String home;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String work;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String allMails;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmail(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllMails() {
        return allMails;
    }

    public String getAddress() {
        return address;
    }

    public File getPhoto() {
        return new File(photo);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", company='" + company + '\'' +
                ", bday='" + bday + '\'' +
                ", bmonth='" + bmonth + '\'' +
                ", byear='" + byear + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", work='" + work + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstname, that.firstname)) return false;
        if (!Objects.equals(lastname, that.lastname)) return false;
        if (!Objects.equals(nickname, that.nickname)) return false;
        if (!Objects.equals(company, that.company)) return false;
        if (!Objects.equals(bday, that.bday)) return false;
        if (!Objects.equals(bmonth, that.bmonth)) return false;
        if (!Objects.equals(byear, that.byear)) return false;
        if (!Objects.equals(home, that.home)) return false;
        if (!Objects.equals(mobile, that.mobile)) return false;
        if (!Objects.equals(work, that.work)) return false;
        if (!Objects.equals(email1, that.email1)) return false;
        if (!Objects.equals(email2, that.email2)) return false;
        if (!Objects.equals(email3, that.email3)) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (bday != null ? bday.hashCode() : 0);
        result = 31 * result + (bmonth != null ? bmonth.hashCode() : 0);
        result = 31 * result + (byear != null ? byear.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
