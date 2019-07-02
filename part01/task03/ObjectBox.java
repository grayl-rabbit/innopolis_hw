package main.java.part01.lesson03.task03;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * class ObjectBox, stores a collection of Object
 * @author L
 */
public class ObjectBox <T extends Object>{

    public T[] collection;

    public ObjectBox(T[] collection) {
        this.collection = collection;
    }
    /**
     * add object to collection
     * @param value
     */
    public void addObject(T value){
        final int N = collection.length;
        collection = Arrays.copyOf(collection, N + 1);
        collection[N] = value;
    }
    /**
     * check repeated items
     * If the item repeats, delete it
     * @param value
     */
    public void deleteObject(T value){
        Object[] objects = Arrays.stream(collection).filter(elem -> elem != value).toArray();
        collection = (T[]) objects;
    }

    /**
     * display the contents of the collection in a string
     */
    public void dump(){
        String result =  Arrays.stream(collection)
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "collection=" + collection +
                '}';
    }
}
