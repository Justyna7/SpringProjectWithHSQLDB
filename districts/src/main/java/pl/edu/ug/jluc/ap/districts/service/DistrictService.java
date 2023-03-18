package pl.edu.ug.jluc.ap.districts.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.domain.District;
import pl.edu.ug.jluc.ap.districts.repository.AddressRepository;
import pl.edu.ug.jluc.ap.districts.repository.DistrictRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DistrictService {
    final AddressRepository addressRepository;
    final DistrictRepository districtRepository;

    public DistrictService(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Iterable<District> findAllDistricts(){
        return districtRepository.findAll();
    }

    public void learning() {
        // Tx.begin
        Address address1 = new Address("Słowackiego", "13G", "7", "80-257", "Gdańsk");
        Address address2 = new Address("Grunwaldzka", "97", "3", "80-244", "Gdańsk");
        Address address3 = new Address("aleja Wojska Polskiego", "23", "8", "80-277", "Gdańsk");



        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);


        District morenaDistrict = new District("Morena");



        morenaDistrict.setAddresses(addresses);
        morenaDistrict.setArea("1423");
        morenaDistrict.setNumberOfResidents("1925");
        morenaDistrict.setPopulationDensity(String.valueOf((Double.parseDouble(morenaDistrict.getNumberOfResidents())/Double.parseDouble(morenaDistrict.getArea()))));

        District districtRetrieved = districtRepository.save(morenaDistrict);
        address1.setDistrict(morenaDistrict);
        address2.setDistrict(morenaDistrict);
        address3.setDistrict(morenaDistrict);
        addressRepository.saveAll(addresses);
        long id = districtRetrieved.getId();
        Optional<District> districtOpt = districtRepository.findById(id);

        districtOpt.ifPresent(district -> {
            System.out.println(district);
            System.out.println(district.getAddresses().size());
        });

        District borkowoDistrict = new District("Borkowo");

        borkowoDistrict.setArea("1234");
        borkowoDistrict.setNumberOfResidents("126");
        borkowoDistrict.setPopulationDensity(String.valueOf((Double.parseDouble(borkowoDistrict.getNumberOfResidents())/Double.parseDouble(borkowoDistrict.getArea()))));

        districtRepository.save(borkowoDistrict);

        districtRepository.findByArea("1234").forEach(System.out::println);
        districtRepository.findByAreaOrNumberOfResidents("1234", "1925").forEach(System.out::println);

        borkowoDistrict.setArea("1213");
        districtRepository.save(borkowoDistrict);
        districtRepository.findByPopulationDensityAndNumberOfResidents(borkowoDistrict.getPopulationDensity(), borkowoDistrict.getNumberOfResidents()).forEach(System.out::println);

        // Tx.commit
        // Tx.rollback
    }
}
