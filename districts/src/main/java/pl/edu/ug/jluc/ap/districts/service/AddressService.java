package pl.edu.ug.jluc.ap.districts.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.domain.District;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.repository.AddressRepository;
import pl.edu.ug.jluc.ap.districts.repository.DistrictRepository;
import pl.edu.ug.jluc.ap.districts.repository.PersonRepository;

import java.util.*;

@Service
@Transactional
public class AddressService {

    final AddressRepository addressRepository;
    final PersonRepository personRepository;
    final DistrictRepository districtRepository;

    public AddressService(AddressRepository addressRepository, PersonRepository personRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.districtRepository = districtRepository;
    }

    public District addDistrict(District district) {
        return districtRepository.save(district);
    }
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

//    public Iterable<Person> findAllPersons(){
//        return personRepository.findAll();
//    }

    public void learning() {
        // Tx.begin
        Date d1 = new Date(1993, Calendar.JANUARY,3);
        Date d2 = new Date(345678);
        Person person1 = new Person("Iwona", "Nowak", d1);
        Person person2 = new Person("Franciszek", "Nowak", d2);
        District district = districtRepository.findByDistrict("Borkowo").get(0);
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);


        Address address = new Address("aleja Wojska Polskiego", "24", "3", "80-277", "Gdańsk");
        address.setDistrict(district);
        Address addressRetrieved = addressRepository.save(address);
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        person1.setAddresses(addresses);
        person2.setAddresses(addresses);
        personRepository.saveAll(people);
        address.setPeople(people);




        Optional<Address> addressOpt = addressRepository.findById(addressRetrieved.getId());

        addressOpt.ifPresent(a -> {
            System.out.println(a);
            System.out.println(a.getPeople().size());
        });

        Address address2 = new Address("aleja Wojska Polskiego", "12", "10", "80-277", "Gdańsk");


        address2.setPeople(people);//new ArrayList<Person>()
        address2.setDistrict(district);
        addressRepository.save(address2);
        addresses.add(address2);
        person1.setAddresses(addresses);
        person2.setAddresses(addresses);
        personRepository.save(person1);
        personRepository.save(person2);
//        personRepository.saveAll(people);

        addressRepository.findByLocality("Gdańsk").forEach(System.out::println);
        addressRepository.findByLocalityAndStreet("Gdańsk", "aleja Wojska Polskiego").forEach(System.out::println);

        address2.setHouseNumber("11");
        addressRepository.save(address2);
        addressRepository.findByLocalityOrDistrict("Frankfurt", district).forEach(System.out::println);

        addressRepository.getAllAddresss().forEach(System.out::println);
        //addressRepository.findAll().forEach(System.out::println);
        // Tx.commit
        // Tx.rollback
    }

}

