package pl.edu.ug.jluc.ap.districts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.jluc.ap.districts.domain.Person;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByName(String name);
    List<Person> findByNameOrSurname(String name, String surname);
    List<Person> findByNameAndMarriage(String name, Person marriage);
//    List<Person> findRandom();

    // Query: JPQL objects
    @Query("Select p from Person p join fetch p.addresses")
    List<Person> getAllPersons();

    // Query: JPQL objects
    @Query("Select p from Person p join fetch p.addresses where p.name=?1 or p.surname=?2")
    List<Person> getByNameOrSurname(String name, String surname);

    // Query: JPQL objects
    @Query("Select p from Person p join fetch p.addresses where p.name=?1 and p.marriage=?2")
    List<Person> getByNameOrMarriage(String name, Person marriage);

    // Query: relational SQL
    @Query(nativeQuery = true, value = "Select * from Person p inner join p.addresses a where surname=?")
    List<Person> getBySurname(String surname);

    // Query: relational SQL
    // left join fetch p.addresses a
    @Query(nativeQuery = true, value = "Select * from Person p order by rand() limit 1")
    List<Person> getRandom();
}