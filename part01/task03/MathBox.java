package main.java.part01.lesson03.task03;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MathBox<T extends Number> extends ObjectBox<T>{

    public MathBox(T[] arr) {
        super(arr);
        checkDouble(arr);
    }

    /**
     * check repeated items
     * If the item repeats, delete it
     * @param arr
     */
    private void checkDouble(T[] arr) {
        try {
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].equals(arr[i])) {
                        throw new RuntimeException("Items cannot be repeated");
                    }
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * alternately dividing all the elements stored
     * in the object by the divisor
     * @param val
     * @param <V>
     */
    public <V> void splitter(V val){

        if(val instanceof Number && !val.equals(0)){
            for (int i = 0; i < collection.length; i++) {
                Number item = collection[i];
                Number number = (Number) val;
                Number value = item.doubleValue() / number.doubleValue();
                collection[i] = ((T)value);
            }
        }else{
            throw new IllegalArgumentException("Illegal param");
        }

    }

    /**
     * sum of all elements
     * @return the sum of all elements of the collection
     */
    public  Double summator() {
        return Arrays.stream(collection)
                .map(Number::doubleValue)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0D);
    }

    /**
     * remove value if found
     * @param i
     */
    public void removeIteration(Integer i){
        int amount = countDouble(i);
        T[] objects = (T[]) Array.newInstance(Number.class, collection.length - amount);

        int index = 0;
        for (T elem: collection) {
            if (elem.intValue() != i) {
                objects[index] = elem;
                index++;
            }
        }
        collection = objects;
    }

    /**
     * count of repeated elements
     * @param i
     * @return
     */
    private int countDouble(Integer i){
        int amount = 0;
        for (T elem: collection) {
            if (elem.intValue() == i) {
                amount++;
            }
        }
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox<?> mathBox = (MathBox<?>) o;
        return Arrays.equals(collection, mathBox.collection);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(collection);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "collection=" + Arrays.toString(collection) +
                '}';
    }
}