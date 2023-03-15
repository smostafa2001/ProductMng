package ir.ac.qom.final_project.data.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Stack;
import java.util.logging.Logger;

public class ConnectionUtility {

    Logger logger = Logger.getLogger(ConnectionUtility.class.getName());
    Stack<int> list = new Stack<int>()

    //    public static Connection getConnection() {
//        try {
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/practical_workshop";
//            String username = "postgres";
//            String password = "123456";
//            Connection connection =
//                    DriverManager.getConnection(url, username, password);
//            connection.setAutoCommit(false);
//            return connection;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//            return null;
//        }
//    }
    public static Connection getConnection() {
        try {
            InputStream is = ConnectionUtility.class.getClassLoader().getResourceAsStream("META-INF/db.properties");
            Properties properties = new Properties();
            properties.load(is);
            Class.forName(properties.getProperty("db.driver"));
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}
