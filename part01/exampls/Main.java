package main.java.part01.lesson06.exampls;/*
 *  26.05.2019
 *  main.java.part01.lesson06.exampls
 *  innopolis
 *  @author L
 */

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
