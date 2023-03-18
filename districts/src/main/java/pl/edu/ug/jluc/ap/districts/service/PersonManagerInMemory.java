package pl.edu.ug.jluc.ap.districts.service;

import org.springframework.stereotype.Service;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.repository.AddressRepository;
import pl.edu.ug.jluc.ap.districts.repository.PersonRepository;

import java.util.List;

@Service
public class PersonManagerInMemory implements PersonManager{

    final PersonRepository personRepository;
    final AddressRepository addressRepository;
    public PersonManagerInMemory(AddressRepository addressRepository, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Person addPerson(Person person) {
        Person personDoDodania = new Person(
                person.getName(),
                person.getSurname(),
                person.getDateOfBirth());
        personRepository.save(personDoDodania);
        return personDoDodania;
    }

    @Override
    public Person editPerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> printAllPeople() {
        personRepository.getAllPersons().forEach(System.out::println);
        return personRepository.getAllPersons();
    }

    @Override
    public boolean deletePerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null){
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }

    @Override
    public Person printPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person printRandomPerson() {
        return personRepository.getRandom().get(0);
    }

}