package main.java.part01.lesson12.task03;

import java.sql.*;

import static main.java.part01.lesson12.SettingsDB.*;

/**
 * Make parameterized selection by login_ID and name at the same time
 * @author L
 */
public class Main {
    public static void main(String[] args) {
        Connection connection;
        Statement statement;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String tableName = "\"user\"";

            String sql = String.format("select * from %s where login_ID=%s and name=%s",
                    tableName, "'login1'", "'Name 1'");
            ResultSet result  = statement.executeQuery(sql);

            while (result.next()){
                System.out.println(result.toString());
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
