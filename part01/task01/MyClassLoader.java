package main.java.part01.lesson08.task01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to load .class
 * @author L
 */
public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("main.java.part01.lesson08.task01.SomeClass".equals(name)) {
            return findClass(name);

        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts work: " + name);

        if ("main.java.part01.lesson08.task01.SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("src/main/java/part01/lesson08/task01/SomeClass.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
