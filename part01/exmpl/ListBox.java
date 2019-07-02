package main.java.part01.lesson03.exmpl;/*
 *  22.04.2019
 *  main.java.part01.lesson03
 *  innopolis
 *  @author L
 */

import java.util.ArrayList;
import java.util.List;

public class ListBox<V> {
    List<V> list = new ArrayList<>();

    void putAll(List<? extends V> items){
        list.addAll(items);
    }

}
