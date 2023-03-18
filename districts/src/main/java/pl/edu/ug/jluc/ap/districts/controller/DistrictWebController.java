package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.ug.jluc.ap.districts.domain.District;
import pl.edu.ug.jluc.ap.districts.service.DistrictManager;
@Controller
public class DistrictWebController {
    private final DistrictManager districtManager;

    public DistrictWebController(@Autowired DistrictManager districtManager) {
        this.districtManager = districtManager;
    }

    @GetMapping("/district")
    public String allDistricts(Model model) {
        model.addAttribute("allDistrictsFromDB", districtManager.printAllDistricts());
        return "district-all";
    }

    @GetMapping("/district/form")
    public String editDistrict (Model model) {
        model.addAttribute("districtToAdd", new District("unknown", "unknown","unknown","unknown"));
        return "district-edit";
    }

    @PostMapping("/district")
    public String dodajNowaDistricts(@ModelAttribute District districtToAdd, Model model) {
        districtManager.addDistrict(districtToAdd);
        model.addAttribute("allDistrictsFromDB", districtManager.printAllDistricts());
        return "district-add";
    }

    @GetMapping("/district/delete/{name}")
    public String usunDistricts (@PathVariable("id") Long id, Model model) {
        if (districtManager.deleteDistrict(id)) {
            model.addAttribute("successMessage", "Operation succesful");
            //  return "success";
        } else {
            model.addAttribute("errorMessage", "Operation unsuccesful");
//    return "error";
        }
        return "redirect:/district";
    }


}



