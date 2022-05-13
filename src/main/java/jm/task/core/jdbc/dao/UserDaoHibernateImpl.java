package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS  users (" +
         "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
         "name VARCHAR(100) ," +
         "lastname VARCHAR(100) , " +
         "age TINYINT (3))";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private final String ADD_USER = "INSERT INTO users(name, lastName, age) VALUES (?1, ?2, ?3)";
    private final String REMOVE_USER = "DELETE FROM users WHERE id=?";

    private final String GET_USERS_LIST = "SELECT id, name, lastname, age FROM users";

    private final String CLEAN_USER_TABLE = "TRUNCATE TABLE users";

    SessionFactory sessionFactory = Util.getSessionFactory();



    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery(CREATE_TABLE, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery(DROP_TABLE, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }



    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery(ADD_USER, User.class)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery(REMOVE_USER, User.class)
                    .setParameter(1, id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() { // не нашёл как сделать через createNativeQuery
        Session session = sessionFactory.openSession();
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery(CLEAN_USER_TABLE, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
