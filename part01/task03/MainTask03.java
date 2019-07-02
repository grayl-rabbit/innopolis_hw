package main.java.part01.lesson03.task03;

/**
 * Main class for task03
 * MathBox extends ObjectBox
 * @author L
 */
public class MainTask03 {
    public static void main(String[] args) {
        MathBox<Number> result = new MathBox<>(new Number[]{1L, 1D, 2L, 3, 1D, 4d});
        System.out.println(result.toString());
        System.out.println("sum of all elements: " + result.summator());

        result.splitter(4l);
        System.out.println("Result after splitter: " + result.toString());

        result.removeIteration(1);
        System.out.println("Result after removeIteration: " + result.toString());

        result.addObject(1);
        result.addObject(12);
        result.addObject(3);
        System.out.println("List after add: " + result.toString());
        result.deleteObject(3);
        System.out.println("List after remove: " + result.toString());
        result.dump();
    }

}
