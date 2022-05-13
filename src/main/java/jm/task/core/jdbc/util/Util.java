package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/pre-project-1.1.3?useUnicode=true&characterEncoding=utf8"
            ;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.setProperty(Environment.DRIVER, DRIVER);
                settings.setProperty(Environment.URL, URL);
                settings.setProperty(Environment.USER, USERNAME);
                settings.setProperty(Environment.PASS, PASSWORD);
                settings.setProperty(Environment.DIALECT, DIALECT);
                settings.setProperty(Environment.SHOW_SQL, "true");
                settings.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.setProperty(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    ///
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
