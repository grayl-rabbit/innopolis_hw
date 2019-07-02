package main.java.part01.lesson05.task02;


import main.java.part01.lesson05.task02.service.RandomUtilService;
import main.java.part01.lesson05.task02.service.RandomUtilServiceImpl;

/**
 * create some files with random text.
 * These files have a random size
 * The word consists of 1 <= n2 <= 15 letters
 * The sentence consists of 1 <= n1 <= 15 words
 * Words are separated by a single space.
 * Sentence ends with (. |! |?) + " "
 * array of words 1 <= n4 <= 1000. There is a probability that one of
 * the words in this array is included in the following sentence
 * @author L
 */
public class Main {

    public static void main(String[] args){

        RandomUtilService randomUtilService = new RandomUtilServiceImpl();

        int size = randomUtilService.getRandomNumberInRange(1, 1000);
        String[] words = randomUtilService.generateWords(randomUtilService.getRandomNumberInRange(1, 1000));

        randomUtilService.getFiles("src/main/java/part01/lesson05/task02/result",3, size, words,50);

    }






}
