package main.java.part01.lesson07.task01;

import java.io.Serializable;

/**
 * class Person for show serialize
 * @author L
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String first_name;
    private String last_name;
    private int age;
    private City city;
    private transient Animal animal;

    public Person(String fname, String lname, int age, City city, Animal animal){
        this.first_name = fname;
        this.last_name  = lname;
        this.age        = age;
        this.animal = animal;
        this.city = city;
    }

    public void setFirstName(String fname) {
        this.first_name = fname;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void setLastName(String lname) {
        this.first_name = lname;
    }

    public String getLastName() {
        return this.last_name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", city=" + city.getCity() +
                ", animal=" + animal +
                '}';
    }
}
