package main.java.part01.lesson07.task01.service;

public interface SerializeService {
    void serialize (Object object, String file);
    Object deSerialize(String file);
}
