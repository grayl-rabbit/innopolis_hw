package main.java.part01.lesson06.task01;

import java.math.BigInteger;

/**
 * Class for calc factorial
 * @author L
 */
public class Factorial implements Runnable{

    private int number;

    public Factorial(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    private BigInteger calcFactorial(int number) {
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= number; i++) {
            result = result.multiply(new BigInteger("" + i));
        }
        return result;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        thread.setName("Thread for number: " + number);
        System.out.println(thread.getName() + ". Factorial - " + calcFactorial(number));

    }
}
