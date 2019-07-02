package main.java.part01.lesson01.task03;

import main.java.part01.lesson01.task03.service.BubbleSortringServiceImpl;
import main.java.part01.lesson01.task03.service.QuickSortingServiceImpl;
import main.java.part01.lesson01.task03.service.SortingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main class for task01
 * @author L
 */
public class MainTask03 {

    public static void main(String[] args) {

        List<Person> people = createPersonList(1000);

        SortingService quickSort = new QuickSortingServiceImpl();
        SortingService bubbleSort = new BubbleSortringServiceImpl();

        long startTime = System.currentTimeMillis();

//        quickSort.mainSort(people);
//        quickSort.ageSort(people);
//        quickSort.alphabetSort(people);
//        quickSort.sexSort(people);

//        bubbleSort.ageSort(people);
//        bubbleSort.alphabetSort(people);
//        bubbleSort.sexSort(people);

        bubbleSort.mainSort(people);

        long stopTime = System.currentTimeMillis();
        long elapsedMillis = stopTime-startTime;
        long elapsedSec = elapsedMillis/1000;
        long elapsedMin = elapsedMillis/60000;
        System.out.printf("SortingService took %d minutes and %s secs and %d milliseconds \n",
                elapsedMin,elapsedSec%60, elapsedMillis%1000);

        printAllPerson(people);


    }


    /**
     * Print every person
     * @param people
     */
    private static void printAllPerson(List<Person> people){

        for(int i=0;i<people.size();i++){
            System.out.println("Person: name - " + people.get(i).getName()
                    + "; age - " + people.get(i).getAge()
                    + "; sex - "+ people.get(i).getSex());
        }
    }

    /**
     * generate random person list
     * @param count
     * @return
     */
    private static List<Person> createPersonList(int count){

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            try {
                people.add(new Person("Name" + getRandomNumberInRange(0,100),
                        getRandomNumberInRange(-10,100), Person.Sex.getRandom()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        return people;
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
