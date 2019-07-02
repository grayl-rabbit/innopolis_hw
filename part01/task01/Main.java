package main.java.part01.lesson07.task01;

import main.java.part01.lesson07.task01.service.SerializeService;
import main.java.part01.lesson07.task01.service.SerializeServiceImpl;

/**
 * serializing an Object to a file and deserializing
 * an object from this file
 * @author L
 */
public class Main {

    public static void main(String[] args) {

        SerializeService serializeService = new SerializeServiceImpl();

        String filepath="src/main/java/part01/lesson07/task01/result";
        Animal cat = new Animal("cat", 2, "MyCat");
        City city = new City("MyCity");
        Person person = new Person("John","Frost",22, city, cat);

        serializeService.serialize(person, filepath);
        Object deSerialize = serializeService.deSerialize(filepath);
        System.out.println(deSerialize.toString());

    }

}
