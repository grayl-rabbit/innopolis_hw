package main.java.part01.lesson12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class with main settings to connect to DB
 * checkConnectDB - method to check connect to DB
 */
public final class SettingsDB {

    public SettingsDB() {
    }

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/inno";
    public static final String USER = "postgres";
    public static final String PASS = "root";

    /**
     * method to check connect to DB
     * @return boolean
     */
    public static boolean checkConnectDB(){
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return false;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            return true;
        } else {
            System.out.println("Failed to make connection to database");
            return false;
        }
    }
}
