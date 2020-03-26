package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

public class DBHelper {
    private final SessionFactory sessionFactory;

    public DBHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Groups groupsWithSpecialName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData where group_name = 'TestGroup1'").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public InGroupContacts contactsGroups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<InGroupContactsData> result = session.createQuery("from InGroupContactsData").list();
        for (InGroupContactsData contactsGroup : result) {
            System.out.println(contactsGroup);
        }
        session.getTransaction().commit();
        session.close();
        return new InGroupContacts(result);
    }
}
