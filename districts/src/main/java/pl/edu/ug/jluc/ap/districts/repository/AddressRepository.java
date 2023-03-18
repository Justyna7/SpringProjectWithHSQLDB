package pl.edu.ug.jluc.ap.districts.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.domain.District;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByLocality(String locality);
    List<Address> findByLocalityAndStreet(String locality, String street);
    List<Address> findByLocalityOrDistrict(String locality, District district);
//    List<Address> findByAllAddresses();
    // Query: JPQL objects
    @Query("Select a from Address a join fetch a.people")
    List<Address> getAllAddresss();

//    // Query:  JPQL objects
//    @Query("Select a from Address a join fetch a.district join fetch a.people")
//    List<Address> getByAllAddresses();

    // Query: JPQL objects
    @Query("Select a from Address a join fetch a.people where a.locality=?1 and a.street=?2")
    List<Address> getByLocalityAndStreet(String locality, String street);

    @Query("Select a from Address a join fetch a.people where a.locality=?1 or a.district=?2")
    List<Address> getByLocalityOrDistrict(String locality, String district);

    // Query:  relational SQL
    @Query(nativeQuery = true, value = "Select * join fetch a.people from Address where street=?")
    List<Address> getByStreet(String street);

}