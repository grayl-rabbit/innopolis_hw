package main.java.part01.lesson03.task01;

/**
 * Main class for task01
 * Shows the implementation of methods:
 * 1 - generic class MathBox
 * 2 - summator - return the sum of all elements
 * 3 - splitter - division by argument
 * 4 - remove argument from collection
 * @author L
 */
public class MainTask01 {

    public static void main(String[] args) {
        MathBox<Number> result = new MathBox<>(new Number[]{1L, 1D, 2L, 3, 1D, 4d});
        System.out.println(result.toString());
        System.out.println("sum of all elements: " + result.summator());

        result.splitter(4l);
        System.out.println("Result after splitter: " + result.toString());

        result.removeIteration(1);
        System.out.println("Result after removeIteration: " + result.toString());
    }
}
