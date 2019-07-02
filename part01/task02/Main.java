package main.java.part01.lesson13.task02;


import lesson13.db.DatabaseService;
import lesson13.db.DatabaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static lesson13.db.ConnectionFactory.getConnection;

/**
 * Class to show work with jdbc
 * task02-a - INSERT to table
 * task02-b - insert to table with params
 * task02-c - insert with batch
 *
 * @author L
 */
public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    private static String INSERT_SQL = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
            " values (?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)";

    public static void main(String[] args) {

        DatabaseService databaseService = new DatabaseServiceImpl();

        if (databaseService.checkConnectDB()) {
            Connection connection;
            String sql;
            try {

                connection = getConnection();

                /* - - - task02-a - - -*/

                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, "Name 555");
                preparedStatement.setString(2, "18/12/2005");
                preparedStatement.setString(3, "login 555");
                preparedStatement.setString(4, "city 555");
                preparedStatement.setString(5, "email 555");
                preparedStatement.setString(6, "desc 555");
                preparedStatement.executeUpdate();
                logger.info("created new user - Name 555");


                /* - - - task02-b - - -*/
                String tableName = "\"user\"";
                String fieldsToInsertUser = "name, birthday, login_id, city, email, description";
                String valueToInsertUser = "'Name 2', TO_DATE('18/12/2005', 'DD/MM/YYYY')," +
                        " 'login2', 'city2', 'email2', 'desc2'";
                sql = String.format("insert into %s(%s) values (%s)",
                        tableName, fieldsToInsertUser, valueToInsertUser);
                connection.prepareStatement(sql).execute();
                logger.info("insert into " + tableName + " table data: " + valueToInsertUser);

                /* - - - task02-c - - -*/
                String[] queries = {
                        "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                                " values ('Name Br122', TO_DATE('17/12/2005', 'DD/MM/YYYY')," +
                                " 'loginBr1', 'cityBr1', 'emailBr1', 'descBr1')",
                        "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                                " values ('Name Br222', TO_DATE('17/12/2010', 'DD/MM/YYYY')," +
                                " 'loginBr2', 'cityBr2', 'emailBr2', 'descBr2')",
                        "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                                " values ('Name Br322', TO_DATE('17/12/2015', 'DD/MM/YYYY')," +
                                " 'loginBr3', 'cityBr3', 'emailBr3', 'descBr3')"
                };

                for (String query : queries) {
                    connection.prepareStatement(query).addBatch();
                    logger.info("created new user with batch");
                }

                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
