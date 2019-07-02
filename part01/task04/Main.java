package main.java.part01.lesson13.task04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import static lesson13.db.ConnectionFactory.getConnection;

/**
 * Class to work with savepoint
 * task04-a - set savepoint, do some inserts
 * task04-b - set savepoint, do some inserts(one insert contains bad sql)
 * @author L
 */
public class Main {

    private static Logger logger = LogManager.getLogger(lesson13.task02.Main.class);

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            /* - - - task04-a - - -*/
            System.out.println("Creating savepoint...");
            Savepoint savepointA = connection.setSavepoint("SavepointA");

            try {
                SQL = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                        " values ('Name 4', TO_DATE('17/12/2000', 'DD/MM/YYYY')," +
                        " 'login4', 'city4', 'email4', 'desc4')";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();

                SQL = "insert into \"role\"(name, description) values ('Administration', 'desc for role')";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();

                SQL = "insert into \"user_role\"(user_id, role_id) values (1, 1)";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();

                connection.commit();
                logger.info("created new user, role, bunch");

            } catch (SQLException e) {
                logger.error("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointA);
            }



            /* - - - task04-b - - -*/
            System.out.println("Creating savepoint...");
            Savepoint savepointB = connection.setSavepoint("SavepointB");

            try {
                SQL = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                        " values ('Name 5', TO_DATE('17/12/2000', 'DD/MM/YYYY')," +
                        " 'login5', 'city5', 'email5', 'desc5')";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();

                SQL = "insert into \"role\"(name, description)" +
                        " values ('Administration', 'desc for role5')";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();

                SQL = "inser into \"user_role\"(user_id, role_id)" +
                        " values (2, 1)";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();

                connection.commit();
                logger.info("created new user, role, bunch");

            } catch (SQLException e) {
                logger.error("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointB);
            }


        } catch (SQLException e) {
            logger.error(e.getMessage(), e.getStackTrace());
        } finally {
            if(connection!=null)
                connection.close();
            if(preparedStatement !=null)
                preparedStatement.close();
        }

    }



}
