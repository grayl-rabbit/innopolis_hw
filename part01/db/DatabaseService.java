package main.java.part01.lesson13.db;

import java.util.Map;

public interface DatabaseService {
    Map<String, String> getPropsDB();
    boolean checkConnectDB();
}
