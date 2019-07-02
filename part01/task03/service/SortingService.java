package main.java.part01.lesson01.task03.service;

import main.java.part01.lesson01.task03.Person;

import java.util.List;

public interface SortingService {
    List<Person> sexSort(List<Person> people);
    List<Person> ageSort(List<Person> people);
    List<Person> alphabetSort(List<Person> people);
    List<Person> mainSort(List<Person> people);
}
