package main.java.part01.lesson12.task04;

import java.sql.*;

import static main.java.part01.lesson12.SettingsDB.*;

/**
 * Class to work with savepoint
 * task04-a - set savepoint, do some inserts
 * task04-b - set savepoint, do some inserts(one insert contains bad sql)
 * @author L
 */
public class Main {

    public static void main(String[] args) {
        Connection connection ;
        Statement statement;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            String SQL;
            String getAllUsersSQL = "SELECT * FROM \"user\"";

            printUsers(statement.executeQuery(getAllUsersSQL));


            /* - - - task04-a - - -*/
            System.out.println("\n===========================\n");
            System.out.println("Creating savepoint...");
            Savepoint savepointA = connection.setSavepoint("SavepointA");

            try {
                SQL = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                        " values ('Name 4', TO_DATE('17/12/2000', 'DD/MM/YYYY')," +
                        " 'login4', 'city4', 'email4', 'desc4')";
                statement.executeUpdate(SQL);

                SQL = "insert into \"role\"(name, description)" +
                        " values ('Administration', 'desc for role')";
                statement.executeUpdate(SQL);

                SQL = "insert into \"user_role\"(user_id, role_id)" +
                        " values (1, 1)";
                statement.executeUpdate(SQL);

                connection.commit();

            } catch (SQLException e) {
                System.out.println("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointA);
            }


            printUsers(statement.executeQuery(getAllUsersSQL));

            /* - - - task04-b - - -*/
            System.out.println("Creating savepoint...");
            Savepoint savepointB = connection.setSavepoint("SavepointB");

            try {
                SQL = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                        " values ('Name 5', TO_DATE('17/12/2000', 'DD/MM/YYYY')," +
                        " 'login5', 'city5', 'email5', 'desc5')";
                statement.executeUpdate(SQL);

                SQL = "insert into \"role\"(name, description)" +
                        " values ('Administration', 'desc for role5')";
                statement.executeUpdate(SQL);

                SQL = "inser into \"user_role\"(user_id, role_id)" +
                        " values (2, 1)";
                statement.executeUpdate(SQL);

                connection.commit();

            } catch (SQLException e) {
                System.out.println("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointB);
            }

            printUsers(statement.executeQuery(getAllUsersSQL));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * print id and name all items from table user
     * @param resultSet
     * @throws SQLException
     */
    private static void printUsers(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            System.out.println("id: " + id + ", Name: " + name);
        }
    }

}
