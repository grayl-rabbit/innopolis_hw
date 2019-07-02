package main.java.part01.lesson13.task03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lesson13.db.ConnectionFactory.getConnection;

/**
 * Make parameterized selection by login_ID and name at the same time
 * @author L
 */
public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String login ="login1cvvv";
            String name = "Name 1";

            String selectUserSql = "select * from \"user\" where login_ID=? and name=?";
            preparedStatement = connection.prepareStatement(selectUserSql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, name);
            ResultSet result = preparedStatement.executeQuery();

            if(!result.next()){
                logger.info("No one was found with login = "+login+" and name = "+name);
            }else {
                logger.info("Found some users with login = "+login+" and name = "+name);
            }

            while (result.next()){
                System.out.println(result.getString("id"));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            if(connection!=null)
                connection.close();
            if(preparedStatement !=null)
                preparedStatement.close();
        }
    }
}
