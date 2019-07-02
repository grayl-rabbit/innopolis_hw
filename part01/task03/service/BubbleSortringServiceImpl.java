package main.java.part01.lesson01.task03.service;

import main.java.part01.lesson01.task03.Person;

import java.util.List;

public class BubbleSortringServiceImpl implements SortingService {
    /**
     * sort by sex(men higher)
     * @param people
     * @return list people
     */
    @Override
    public List<Person> sexSort(List<Person> people) {
        mainSortByType(people, "sex");
        return people;
    }

    /**
     * sort by age(higher older)
     * @param people
     * @return list people
     */
    @Override
    public List<Person> ageSort(List<Person> people) {
        mainSortByType(people, "age");
        return people;
    }

    /**
     * sort by alphabet
     * @param people
     * @return list people
     */
    @Override
    public List<Person> alphabetSort(List<Person> people) {
        mainSortByType(people, "name");
        return people;
    }

    @Override
    public List<Person> mainSort(List<Person> people) {
        mainSort2(people);
        return people;
    }


    /**
     * sort list by type
     * @param people
     * @param type - sort type(age, sex, alphabet)
     */
    private static void mainSortByType(List<Person> people, String type) {

        int length = people.size();
        Person person1;
        Person person2;
        boolean is_sorted;

        for (int i = 0; i < length; i++) {
            is_sorted = true;
            for (int j = 1; j < (length - i); j++) {

                person1 = people.get(j-1);
                person2 = people.get(j);
                boolean sortVal = false;

                switch (type){
                    case "sex":
                        sortVal = person1.getSex().compareTo(person2.getSex()) > 0;
                        break;
                    case "age":
                        sortVal = person1.getAge() < person2.getAge();
                        break;
                    case "name":
                        sortVal = person1.getName().compareTo(person2.getName()) > 0;
                        break;
                    default:
                        break;
                }

                if(sortVal) {
                    people.set((j-1), person2);
                    people.set(j, person1);
                    is_sorted = false;
                }

                checkDouble(person1, person2);

            }
            if (is_sorted) break;
        }

    }

    /**
     * sort list by sex then age and alphabet
     * @param people
     */
    private static void mainSort2(List<Person> people) {

        int length = people.size();
        Person person1;
        Person person2;
        boolean is_sorted;

        for (int i = 0; i < length; i++) {
            is_sorted = true;
            for (int j = 1; j < (length - i); j++) {

                person1 = people.get(j-1);
                person2 = people.get(j);


                if( person1.getSex().compareTo(person2.getSex()) > 0 ) {
                    toSwap(people, j);
                }
                if( person1.getSex().compareTo(person2.getSex())== 0 && person1.getAge() < person2.getAge()){
                    toSwap(people, j);
                }
                if(person1.getAge() == person2.getAge() && person1.getSex().compareTo(person2.getSex())== 0
                        && person1.getName().compareTo(person2.getName()) > 0){
                    toSwap(people,  j);
                }

                checkDouble(person1, person2);
                is_sorted = false;
            }
            if (is_sorted) break;
        }

    }

    /**
     * swap two objects in list
     * @param people
     * @param j - index list
     */
    private static void toSwap(List<Person> people, int j) {
        Person person11 = people.get(j-1);
        Person person22 = people.get(j);

        people.set((j-1), person22);
        people.set(j, person11);
    }


    /**
     * check double entries by name and age
     * @param person1
     * @param person2
     */
    private static void checkDouble(Person person1, Person person2) {
        try{
            if(person1.getName().equals(person2.getName()) && person1.getAge() == person2.getAge()){
                throw new RuntimeException("Same data person: name - " + person1.getName()+"; age - " + person1.getAge());
            }
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


}
