package main.java.part01.lesson04.service;

import main.java.part01.lesson04.Animal;

import java.util.List;
import java.util.UUID;

public interface AnimalService {
    void addAnimal(Animal animal, List<Animal> animals);

    List<Animal> findByNickname(String nickname, List<Animal> animals);
    Animal findByUUID(UUID id, List<Animal> animals);

    void update(Animal newAnimal, List<Animal> animals);

    void sorting(List<Animal> animals);

    boolean isExist(Animal animal, List<Animal> animals);
}
