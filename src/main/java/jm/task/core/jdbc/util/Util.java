package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;

//import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final Connection connection;
    static private SessionFactory sessionFactory;


    public Util() {
        this.connection = getMysqlConnection();
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  SessionFactory getSessionFactory() {
        sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();

            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            //settings.put(Environment.URL,  "jdbc:mysql://localhost:3306/db_example?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
            settings.put(Environment.URL,  "jdbc:mysql://localhost:3306/db_example?useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            //sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }

}
