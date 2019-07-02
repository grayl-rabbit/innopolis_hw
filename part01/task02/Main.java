package main.java.part01.lesson12.task02;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static main.java.part01.lesson12.SettingsDB.*;

/**
 * Class to show work with jdbc
 * task02-a - INSERT to table
 * task02-b - insert to table with params
 * task02-c - insert with batch
 * @author L
 */
public class Main {

    public static void main(String[] args) {
        if(checkConnectDB()){
            Connection connection;
            Statement statement;
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                statement = connection.createStatement();

                /* - - - task02-a - - -*/
                String sql = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                        " values ('Name 1', TO_DATE('17/12/2015', 'DD/MM/YYYY')," +
                        " 'login1', 'city1', 'email1', 'desc1')";
                statement.executeUpdate(sql);


                /* - - - task02-b - - -*/
                String tableName = "\"user\"";
                String fieldsToInsertUser = "name, birthday, login_id, city, email, description";
                String valueToInsertUser = "'Name 2', TO_DATE('18/12/2005', 'DD/MM/YYYY')," +
                        " 'login2', 'city2', 'email2', 'desc2'";
                sql = String.format("insert into %s(%s) values (%s)",
                        tableName, fieldsToInsertUser, valueToInsertUser);
                statement.executeUpdate(sql);


                /* - - - task02-c - - -*/
                String [] queries = {
                        "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                                " values ('Name Br1', TO_DATE('17/12/2005', 'DD/MM/YYYY')," +
                                " 'loginBr1', 'cityBr1', 'emailBr1', 'descBr1')",
                        "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                                " values ('Name Br2', TO_DATE('17/12/2010', 'DD/MM/YYYY')," +
                                " 'loginBr2', 'cityBr2', 'emailBr2', 'descBr2')",
                        "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                                " values ('Name Br3', TO_DATE('17/12/2015', 'DD/MM/YYYY')," +
                                " 'loginBr3', 'cityBr3', 'emailBr3', 'descBr3')"
                };

                for (String query : queries) {
                    statement.addBatch(query);
                }

                statement.executeBatch();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
