package main.java.part01.lesson04;

import java.util.UUID;

/**
 * Class Animal describes pet properties
 * @author L
 */
public class Animal implements Comparable<Animal> {
    private UUID id;
    private String nickname;
    private Person owner;
    private int weight;

    public Animal(String nickname, Person owner, int weight) {
        this.id = setId();
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    public Animal(UUID id, String nickname, Person owner, int weight) {
        this.id = id;
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    public UUID getId() {
        return id;
    }

    public UUID setId() {
        return this.id = UUID.randomUUID();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * sorting animals list
     * @param animal
     * @return
     */
    @Override
    public int compareTo(Animal animal) {

        int c;
        c = this.getOwner().getName().compareTo(animal.getOwner().getName());
        if (c == 0)
            c = this.getNickname().compareTo(animal.getNickname());
        if (c == 0)
            c = Integer.compare(this.getWeight(), animal.getWeight());
        return c;
    }
}
