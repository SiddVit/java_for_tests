package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InGroupContacts extends ForwardingSet<InGroupContactsData> {
    private Set<InGroupContactsData> delegate;

    public InGroupContacts(InGroupContacts inGroupContacts) {
        this.delegate = new HashSet<InGroupContactsData>(inGroupContacts.delegate());
    }

    public InGroupContacts(Collection<InGroupContactsData> inGroupContacts) {
        this.delegate = new HashSet<InGroupContactsData>(inGroupContacts);
    }

    public InGroupContacts() {
        this.delegate = new HashSet<InGroupContactsData>();
    }

    @Override
    public Set<InGroupContactsData> delegate() {
        return delegate;
    }

    public InGroupContacts withAdded(InGroupContactsData inGroupContact) {
        InGroupContacts inGroupContacts = new InGroupContacts(this);
        inGroupContacts.add(inGroupContact);
        return inGroupContacts;
    }


    public InGroupContacts withOut(InGroupContactsData inGroupContact) {
        InGroupContacts inGroupContacts = new InGroupContacts(this);
        inGroupContacts.remove(inGroupContact);
        return inGroupContacts;
    }
}
