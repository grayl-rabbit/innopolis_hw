package main.java.part01.lesson14.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface UserCRUDService {
    void insert(Map<String, String> params) throws SQLException;
    ResultSet select() throws SQLException;
    void update(String name, int id) throws SQLException;
    void delete(int id) throws SQLException;
}
