package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String bmonth;
    private final String bday;
    private final String byear;

    public ContactData(String firstname, String lastname, String nickname, String company, String bmonth, String bday, String byear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.bmonth = bmonth;
        this.bday = bday;
        this.byear = byear;
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

    public String getBmonth() {
        return bmonth;
    }

    public String getBday() {
        return bday;
    }

    public String getByear() {
        return byear;
    }
}
