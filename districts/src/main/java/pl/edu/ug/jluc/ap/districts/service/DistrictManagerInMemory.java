package pl.edu.ug.jluc.ap.districts.service;

import org.springframework.stereotype.Service;
import pl.edu.ug.jluc.ap.districts.domain.District;
import pl.edu.ug.jluc.ap.districts.repository.AddressRepository;
import pl.edu.ug.jluc.ap.districts.repository.DistrictRepository;

import java.util.List;

@Service
public class DistrictManagerInMemory implements DistrictManager{

    final DistrictRepository districtRepository;
    final AddressRepository addressRepository;
    public DistrictManagerInMemory(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public District addDistrict(District district) {
        District districtDoDodania = new District(
                district.getDistrict(),
                district.getNumberOfResidents(),
                district.getArea(),
                district.getPopulationDensity());
        districtRepository.save(districtDoDodania);
        return districtDoDodania;
    }

    @Override
    public District editDistrict (District district) {
        return districtRepository.save(district);
    }

    @Override
    public List<District> printAllDistricts() {
        districtRepository.getAllDistricts().forEach(System.out::println);
        return districtRepository.getAllDistricts();
    }

    @Override
    public boolean deleteDistrict(Long id) {
        District district = districtRepository.findById(id).orElse(null);
        if (district == null){
            return false;
        }
        districtRepository.deleteById(id);
        return true;
    }

    public District printDistrict(Long id) {
        return districtRepository.findById(id).orElse(null);
    }
}

