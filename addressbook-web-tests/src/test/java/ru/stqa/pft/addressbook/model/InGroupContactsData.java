package ru.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_in_groups")
public class InGroupContactsData {
    @Column(name = "id")
    @Id
    private int contactId;

    @Column(name = "group_id")
    private int groupId;

    public int getContactId() {
        return contactId;
    }

    public int getGroupId() {
        return groupId;
    }

    public InGroupContactsData withContactId(int contactId) {
        this.contactId = contactId;
        return this;
    }

    public InGroupContactsData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public String toString() {
        return "InGroupContactsData{" +
                "contactId=" + contactId +
                ", groupId=" + groupId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InGroupContactsData that = (InGroupContactsData) o;

        if (contactId != that.contactId) return false;
        return groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + groupId;
        return result;
    }
}
