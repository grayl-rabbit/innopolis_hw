package main.java.part01.lesson01.task02;

import java.util.Random;

/**
 * sqrt random number
 * @author L
 */
public class SqrtRandomNumber {

    public void check(int count){
        double[] arr = new double[count];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomNumberInRange(-80, 10000);
        }

        try {

            for (double k : arr) {

                if(Math.signum(k)<0){
                    throw new Exception("\n -_- awful. this is a negative number: " + k);
                }

                double q = Math.sqrt(k);
                if (Math.pow((int) q, 2) == (int) k) {
                    System.out.println("\n ^.^ lucky number: " + q);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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

}
