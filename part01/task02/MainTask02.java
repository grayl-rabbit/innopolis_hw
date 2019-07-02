package main.java.part01.lesson03.task02;

import java.util.ArrayList;

/**
 * Main class for task02
 * Shows work methods:
 * addObject - add object to collection
 * removeObject - remove item
 * dump - collection to string
 * @author L
 */
public class MainTask02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("A");

        ObjectBox objectBox = new ObjectBox(list);

        System.out.println("Initial list: " + objectBox.toString());

        objectBox.addObject(5);
        System.out.println("List after add: " + objectBox.toString());
        objectBox.removeObject("A");
        System.out.println("List after remove: " + objectBox.toString());
        objectBox.dump();
    }
}
