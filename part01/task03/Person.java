package main.java.part01.lesson01.task03;

/**
 * This class create Person object
 * @author L
 */
public class Person {
    private String name;
    private int age;
    private Sex sex;

    public Person(String name, int age, Sex sex) {
        this.name = name;
        setAge(age);
        this.sex = sex;
    }

    public enum Sex {
        MAN,
        WOMAN;
        public static Sex getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if(age >100 || age<0 ){
            throw new IllegalArgumentException("Age should be in the range of [0-100]. Age = "+ age);
        }
        this.age = age;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


}
