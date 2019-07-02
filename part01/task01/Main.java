package main.java.part01.lesson06.task01;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * There is an array of random numbers.
 * Calculate the factorial of all elements of the array.
 * Use thread pool to solve the problem.
 * @author L
 */

public class Main {

    public static void main(String[] args) {
        
        int[] numbers = generateNumbers(1000);

        List<Factorial> threads = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            Factorial factorial = new Factorial(numbers[i]);
            threads.add(factorial);
        }
        threads.parallelStream().forEach(Factorial::run);
    }


    /**
     * generate random number
     * @param min
     * @param max
     * @return
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * generate random array with int
     * @param count
     * @return
     */
    private static int[] generateNumbers(int count){
        int[] list = new int[count];
        for (int i = 0; i < count; i++) {
            list[i] = getRandomNumberInRange(1, 100);
        }
        return list;
    }



}
