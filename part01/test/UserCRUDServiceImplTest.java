package main.java.part01.lesson14.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static db.ConnectionFactory.getConnection;

@DisplayName("Simple tests")
class UserCRUDServiceImplTest {

    private static UserCRUDService userCRUDService;
    private static Connection connection;

    static {
        try {
            userCRUDService = new UserCRUDServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void beforeAll() throws SQLException {
        System.out.println("Before all test methods");
        connection = getConnection();
    }


    @Test
    void test() throws SQLException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name","Name 555");
        params.put("birthday","18/12/2005");
        params.put("login","login 555");
        params.put("city","city 555");
        params.put("email","email 555");
        params.put("desc","desc 555");

        userCRUDService.insert(params);
    }

    @Test
    void test2() throws SQLException {
        ResultSet resultSet = userCRUDService.select();
        while (resultSet.next()){
            System.out.println(resultSet.getString("id"));
        }
    }


    @Test
    void test3() throws SQLException{
        userCRUDService.update("Test name", 1);
        ResultSet resultSet = userCRUDService.select();
        while (resultSet.next()){
            System.out.println(resultSet.getString("id"));
        }
    }

    @Test
    void test4() throws SQLException{
        userCRUDService.delete(2);
    }


    @AfterAll
    static void afterAll() throws SQLException {
        System.out.println("After all test methods");
        connection.close();
    }

}