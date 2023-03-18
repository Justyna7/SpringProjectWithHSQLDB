package pl.edu.ug.jluc.ap.districts.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.jluc.ap.districts.domain.District;

import java.util.List;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {

    List<District> findByArea(String area);
    List<District> findByAreaOrNumberOfResidents(String area, String numberOfResidents);
    List<District> findByPopulationDensityAndNumberOfResidents(String populationDensity, String numberOfResidents);
    List<District> findByDistrict(String district);

    // Query: JPQL objects
    @Query("Select d from District d join fetch d.addresses")
    List<District> getAllDistricts();

    // Query: JPQL objects
    @Query("Select d from District d join fetch d.addresses where d.district=?1 ")
    List<District> getByDistrict(String district);

    // Query: JPQL objects
    @Query("Select d from District d join fetch d.addresses where d.area=?1 or d.numberOfResidents=?2")
    List<District> getByAreaOrNumberOfResidents(String area, String numberOfResidents);

    // Query: JPQL objects
    @Query("Select d from District d join fetch d.addresses where d.populationDensity=?1 and d.numberOfResidents=?2")
    List<District> getByPopulationDensityAndNumberOfResidents(String populationDensity, String numberOfResidents);

    // Query: relational SQL
    @Query(nativeQuery = true, value = "Select * from District join fetch d.addresses where numberOfResidents=?")
    List<District> getByNumberOfResidents(String numberOfResidents);


}
