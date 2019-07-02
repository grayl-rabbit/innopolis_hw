package main.java.part01.lesson04;

import main.java.part01.lesson04.service.AnimalService;
import main.java.part01.lesson04.service.AnimalServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Main class for task01 - pet file
 * addAnimal - add animal to the general list
 * findByNickname - search for an animal by its nickname
 * update - change of animal data by its identifier
 * sorting - sorting pet list
 * @author L
 */
public class Main {
    public static void main(String[] args) {

        AnimalService animalService = new AnimalServiceImpl();

        List<Animal> animals = createAnimalList(10);

        Person testPerson = new Person(" Test Name", 55, Person.Sex.MAN);

        animalService.findByNickname("Nickname1", animals);

        UUID id = animals.get(0).getId();
        Animal animal = new Animal(id, "Updated pet", createPerson(), 999);
        animalService.update(animal, animals);

        animalService.sorting(animals);

        animalService.addAnimal(new Animal("test", testPerson,666), animals);
        printAllAnimals(animals);
    }


    /**
     * generate random animal list
     * @param count
     * @return
     */
    private static List<Animal> createAnimalList(int count){
        List<Animal> animals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            try {
                animals.add(new Animal("Nickname" + getRandomNumberInRange(0,5),
                        createPerson(), getRandomNumberInRange(0,50)));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        return animals;
    }

    /**
     * generate random Person
     * @return
     */
    private static Person createPerson(){
        return new Person("Name" + getRandomNumberInRange(0,100),
                getRandomNumberInRange(0,100), Person.Sex.getRandom());
    }

    /**
     * generate random number
     * @param min
     * @param max
     * @return
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Print every animal
     * @param animals
     */
    private static void printAllAnimals(List<Animal> animals){

        for(int i=0;i<animals.size();i++){
            System.out.println("Animal: Nickname - " + animals.get(i).getNickname()
                    + "; Owner - " + animals.get(i).getOwner().getName()
                    + "; Weight - "+ animals.get(i).getWeight());
        }
    }
}
