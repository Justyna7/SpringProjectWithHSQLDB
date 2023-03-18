package pl.edu.ug.jluc.ap.districts.service;

import pl.edu.ug.jluc.ap.districts.domain.District;

import java.util.List;

public interface DistrictManager {
    District addDistrict(District district);
    District editDistrict(District district);
    List<District> printAllDistricts();
    boolean deleteDistrict(Long id);
    District printDistrict(Long id);
}





