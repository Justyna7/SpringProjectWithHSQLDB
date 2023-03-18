package pl.edu.ug.jluc.ap.districts.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.domain.District;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.repository.AddressRepository;
import pl.edu.ug.jluc.ap.districts.repository.DistrictRepository;
import pl.edu.ug.jluc.ap.districts.repository.PersonRepository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class PersonService {
    final AddressRepository addressRepository;
    final PersonRepository personRepository;
    final DistrictRepository districtRepository;


    public PersonService(AddressRepository addressRepository, PersonRepository personRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.districtRepository = districtRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Iterable<Person> findAllPersons(){
        return personRepository.findAll();
    }

    public void learning() {
        // Tx.begin
        Address address1 = new Address("Słowackiego", "13A", "5", "80-257", "Gdańsk");
        Address address2 = new Address("Grunwaldzka", "18", "15", "80-244", "Gdańsk");
        District district = districtRepository.findByDistrict("Borkowo").get(0);
        address1.setDistrict(district);
        address2.setDistrict(district);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
        addressRepository.saveAll(addresses);

        Date d1 = new Date(341678);
        Person person1 = new Person("Krystyna", "Kowalska", d1);
        person1.setAddresses(addresses);
        Person personRetrieved = personRepository.save(person1);
        List<Person>l1 = new ArrayList<>();
        l1.add(person1);
        address1.setPeople(l1);
        address2.setPeople(l1);
        addressRepository.save(address1);
        addressRepository.save(address2);
        Optional<Person> personOpt = personRepository.findById(personRetrieved.getId());

        personOpt.ifPresent(person -> {
            System.out.println(person);
            System.out.println(person.getAddresses().size());
        });

        Date d2 = new Date(349678);
        Person person2 = new Person("Marcin", "Kowalski", d2);
        personRepository.save(person2);
        person1.setMarriage(person2);
        person2.setMarriage(person1);
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.findByName("Krystyna").forEach(System.out::println);
        personRepository.findByNameOrSurname("Marcin", "Kowalski").forEach(System.out::println);
        personRepository.findByNameAndMarriage("Marcin", person1).forEach(System.out::println);
        Person o = personRepository.getRandom().get(0);
        System.out.println(o);







        // Tx.commit
        // Tx.rollback
    }
    @Transactional
    public Flux<Person> personStream() {

        System.out.println("###########################################################JL");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        List<Person> p = personRepository.getAllPersons();

        Flux<Person> events = Flux.fromStream(Stream.generate(() -> {
            Collections.shuffle(p);
            return p.get(0);
        }));

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Flux<Person> ret = Flux.zip(events, interval, (e, i) -> e);
        System.out.println(p.get(0));
        return ret;

    }
}
