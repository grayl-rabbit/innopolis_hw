package main.java.part01.lesson10.task01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


/**
 * read any file
 * sorting text by alphabet without double words
 * save list to the file-result
 * work with lambda expression
 * @author L
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Path fileRead = Paths.get("src/main/java/part01/lesson10/task01/bel");

        Path fileWrite = Paths.get("src/main/java/part01/lesson10/task01/result");

        List<String> bel = Files.readAllLines(fileRead);
        List<String> collect = bel.stream()
                .map(String::toLowerCase)
                .sorted()
                .distinct()
                .collect(Collectors.toList());


        Files.write(fileWrite, collect);

    }
}
