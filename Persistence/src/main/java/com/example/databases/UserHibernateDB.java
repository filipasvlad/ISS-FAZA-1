package com.example.databases;

import com.example.User;
import com.example.interfaces.UserInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Properties;

public class UserHibernateDB implements UserInterface {
    static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public UserHibernateDB(Properties props) {
    }

    @Override
    public User findOne(String username) {
        initialize();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                System.out.println("am ajuns aici");
                User user = session.createQuery("from User where username = :username", User.class)
                        .setParameter("username", username)
                        .setMaxResults(1)
                        .uniqueResult();
                System.out.println("am ajuns aici");

                tx.commit();
                System.out.println("am ajuns aici");

                return user;
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                if (tx != null)
                    tx.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User save(User entity) throws FileNotFoundException {
        initialize();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
                return entity;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    @Override
    public User delete(String aLong) {
        return null;
    }

    @Override
    public User update(User entity1, User entity2) {
        return null;
    }
}
