package main.java.part01.lesson04;

/**
 * Class Person  - animal owner.
 * @author L
 */
public class Person {
    private String name;
    private int age;
    private Sex sex;

    public enum Sex {
        MAN,
        WOMAN;
        public static Sex getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
