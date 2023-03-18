package pl.edu.ug.jluc.ap.districts.service;

import pl.edu.ug.jluc.ap.districts.domain.Person;

import java.util.List;

public interface PersonManager {
    Person addPerson(Person person);
    Person editPerson(Person person);
    List<Person> printAllPeople();
    boolean deletePerson(Long id);
    Person printPerson(Long id);

    Person printRandomPerson();
}
