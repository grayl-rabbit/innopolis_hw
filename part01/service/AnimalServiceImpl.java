package main.java.part01.lesson04.service;
import main.java.part01.lesson04.Animal;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AnimalServiceImpl implements AnimalService {

    /**
     * find animal by nickname
     * @param nickname - animal nickname
     * @param animals animals list
     * @return animals list
     */
    @Override
    public List<Animal> findByNickname(String nickname, List<Animal> animals) {
        return animals.stream()
                .filter(animal -> nickname.equals(animal.getNickname()))
                .collect(Collectors.toList());
    }

    /**
     * update animal data
     * @param newAnimal - new data for object
     * @param animals - animals list
     */
    @Override
    public void update(Animal newAnimal, List<Animal> animals){
        Animal animal = findByUUID(newAnimal.getId(), animals);
        animal.setNickname(newAnimal.getNickname());
        animal.setOwner(newAnimal.getOwner());
        animal.setWeight(newAnimal.getWeight());
    }

    /**
     * find animal by UUID
     * @param id - UUID
     * @param animals - animals list
     * @return Animal
     */
    @Override
    public Animal findByUUID(UUID id, List<Animal> animals){
        return animals.stream()
                .filter(item -> id.equals(item.getId()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("animal not found"));
    }

    /**
     * Sorting animal list
     * @param animals - animals list
     */
    @Override
    public void sorting(List<Animal> animals){
        Collections.sort(animals);
    }

    /**
     * add animal to animal list
     * if animal doesn't exist
     * @param animal - animal to add
     * @param animals - animals list
     */
    @Override
    public void addAnimal(Animal animal, List<Animal> animals){
        try {
            if (!isExist(animal, animals)) {
                animals.add(animal);
            } else {
                throw new RuntimeException("The animal is already exist");
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * check the existence of an animal in the list
     * @param animal - animal sought
     * @param animals - animals list
     * @return
     */
    @Override
    public boolean isExist(Animal animal, List<Animal> animals){

        return animals.stream().anyMatch(animal1 -> animal1.getNickname().equals(animal.getNickname())
                && (animal1.getWeight() == animal.getWeight())
                && animal1.getOwner() == animal.getOwner());
    }

}