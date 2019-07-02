package main.java.part01.lesson14.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static main.java.part01.lesson14.db.ConnectionFactory.getConnection;

/**
 * class with simple methods CRUD
 * @author L
 */
public class UserCRUDServiceImpl implements UserCRUDService {
    private Connection connection = getConnection();

    public UserCRUDServiceImpl() throws SQLException {
    }

    public void insert(Map<String, String> params) throws SQLException {
        String INSERT_SQL = "insert into \"user\"(name, birthday, login_id, city, email, description)" +
                " values (?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
        preparedStatement.setString(1, params.get("name"));
        preparedStatement.setString(2,  params.get("birthday"));
        preparedStatement.setString(3,  params.get("login"));
        preparedStatement.setString(4,  params.get("city"));
        preparedStatement.setString(5,  params.get("email"));
        preparedStatement.setString(6,  params.get("desc"));
        preparedStatement.executeUpdate();
    }

    public ResultSet select() throws SQLException {
        String SELECT_SQL = "select * from \"user\" ";

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
        return preparedStatement.executeQuery();
    }

    public void update(String name, int id) throws SQLException {
        String UPDATE_SQL = "update \"user\" set name = ? where id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2,  id);
        preparedStatement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String DELETE_SQL = "delete from \"user\" where id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
