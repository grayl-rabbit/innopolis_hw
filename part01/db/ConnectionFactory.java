package main.java.part01.lesson14.db;//package main.java.part01.lesson13.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class to create connect to DB
 * @author L
 */
public class ConnectionFactory {
    private static BasicDataSource dataSource;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        DatabaseService databaseService = new DatabaseServiceImpl();
        Map<String, String> propsDB = databaseService.getPropsDB();

        try {
            Class.forName(propsDB.get("driver"));
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return DriverManager
                .getConnection(propsDB.get("host"), propsDB.get("login"), propsDB.get("password"));
    }



}
