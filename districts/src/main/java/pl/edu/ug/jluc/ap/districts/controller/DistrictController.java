package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.jluc.ap.districts.domain.District;
import pl.edu.ug.jluc.ap.districts.service.DistrictManager;

import java.util.List;

@RestController
public class DistrictController {
    private final DistrictManager districtManager;


    public DistrictController(@Autowired DistrictManager districtManager) {
        this.districtManager = districtManager;
    }

    @PostMapping("/api/district")
//    @RequestMapping(
//            value = "/api/district",
//            method = RequestMethod.POST,
//            consumes= MediaType.APPLICATION_JSON_VALUE // + "; charset=utf-8"//MediaType.APPLICATION_JSON_UTF8_VALUE//MediaType.APPLICATION_JSON_VALUE //"application/json"
//    )
    @ResponseBody
    District addDistrict(@RequestBody District district) {
        return districtManager.addDistrict(district);
    }

    @PutMapping("/api/district")
    District modifyDistrict(@RequestBody District district) {
        District d = districtManager.printDistrict(district.getId());
        if (d == null) {
            throw new DistrictNotFoundException();
        }
        return districtManager.editDistrict(district);
    }

    @GetMapping("/api/district")
    List<District> getAll() {
        return districtManager.printAllDistricts();
    }

    @GetMapping("/api/district/{id}")
    District findDistrictByName(@PathVariable("id") Long id) {
        District d = districtManager.printDistrict(id);
        if (d == null) {
            throw new DistrictNotFoundException();
        }

        return d;
    }

    @DeleteMapping("/api/district/{id}")
    public void deleteDistrict(@PathVariable("id") Long id){
        districtManager.deleteDistrict(id);
    }


}







