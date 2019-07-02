package main.java.part01.lesson14.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * some methods to work with DB
 * @author L
 */
public class DatabaseServiceImpl implements DatabaseService {


    public DatabaseServiceImpl() {
    }

    /**
     * get db properties to connect
     * @return
     */
    public Map<String, String> getPropsDB(){
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");
            String driver = property.getProperty("db.driver");
            Map<String, String> props = new HashMap<String, String>();

            props.put("host", host);
            props.put("login", login);
            props.put("password", password);
            props.put("driver", driver);

            return props;

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        return null;
    }

    /**
     * method to check connect to DB
     * @return boolean
     */
    public boolean checkConnectDB(){

        Map<String, String> propsDB = getPropsDB();
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName(propsDB.get("driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return false;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection;

        try {
            connection = DriverManager
                    .getConnection(propsDB.get("host"), propsDB.get("login"), propsDB.get("password"));

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
