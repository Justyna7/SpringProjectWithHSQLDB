package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.service.PersonManager;

import java.util.Collections;
import java.util.List;

@RestController
public class PersonController {
    private final PersonManager personManager;


    public PersonController(@Autowired PersonManager personManager) {
        this.personManager = personManager;
    }

    @PostMapping("/api/person")
    Person addPerson(@RequestBody Person person) {
        return personManager.addPerson(person);
    }

    @PutMapping("/api/person")
    Person modifyPerson(@RequestBody Person person) {
        Person d = personManager.printPerson(person.getId());
        if (d == null) {
            throw new PersonNotFoundException();
        }
        return personManager.editPerson(person);
    }

    @GetMapping("/api/person")
    List<Person> getAll() {
        return personManager.printAllPeople();
    }

    @GetMapping("/api/person/random")
    Person findRandomPerson() {
        List<Person> l = personManager.printAllPeople();
        Collections.shuffle(l);
        Person d = l.get(0);
        if (d == null) {
            throw new PersonNotFoundException();
        }

        return d;
    }
    @GetMapping("/api/person/{id}")
    Person findPersonById(@PathVariable("id") Long id) {
        Person d = personManager.printPerson(id);
        if (d == null) {
            throw new PersonNotFoundException();
        }

        return d;
    }

    @DeleteMapping("/api/person/{id}")
    public void deletePerson(@PathVariable("id") Long id){
        personManager.deletePerson(id);
    }


}