package main.java.part01.lesson06.exampls;/*
 *  26.05.2019
 *  main.java.part01.lesson06.exampls
 *  innopolis
 *  @author L
 */

public class MyThread {
    public static void main(String[] args) {


        Thread thread = new Thread(() -> System.out.println("Run thread"));

        thread.start();
    }
}
