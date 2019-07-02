package main.java.part01.lesson14.db;

import java.util.Map;

public interface DatabaseService {
    Map<String, String> getPropsDB();
    boolean checkConnectDB();
}
