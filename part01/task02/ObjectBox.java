package main.java.part01.lesson03.task02;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * class ObjectBox, stores a collection of Object
 * @author L
 */
public class ObjectBox {

    private Collection collection;

    public ObjectBox(Collection collection) {
        this.collection = collection;
    }

    /**
     * add object to collection
     * @param value
     */
    public void addObject(Object value){
        collection.add(value);
    }

    /**
     * check repeated items
     * If the item repeats, delete it
     * @param value
     */
    public void removeObject(Object value){
        if(collection.contains(value)){
            collection.removeIf(n -> Objects.equals(n, value));
        }
    }

    /**
     * display the contents of the collection in a string
     */
    public void dump(){
        String result = (String) collection.stream()
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
