package main.java.part01.lesson03.exmpl;/*
 *  22.04.2019
 *  main.java.part01.lesson03
 *  innopolis
 *  @author L
 */

public class GenericBox<T> {
    private T val;
    public GenericBox(T val){
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }


}
