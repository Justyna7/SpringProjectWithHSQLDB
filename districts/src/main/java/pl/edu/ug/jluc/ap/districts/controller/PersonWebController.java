package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.service.PersonManager;

import java.util.Date;

@Controller
public class PersonWebController {
    private final PersonManager personManager;

    public PersonWebController(@Autowired PersonManager personManager) {
        this.personManager = personManager;
    }

    @GetMapping("/person")
    public String allPeople(Model model) {
        model.addAttribute("allOsobyFromDB", personManager.printAllPeople());
        return "person-all";
    }

    @GetMapping("/person/form")
    public String edycjaOsoby (Model model) {
        model.addAttribute("personToAdd", new Person("unknown", "unknown", new Date()));
        return "person-edit";
    }

    @PostMapping("/person")
    public String dodajNowaOsobe(@ModelAttribute Person personToAdd, Model model) {
        personManager.addPerson(personToAdd);
        model.addAttribute("allOsobyFromDB", personManager.printAllPeople());
        return "person-add";
    }

    @GetMapping("/person/delete/{id}")
    public String usunOsobe (@PathVariable("id") Long id, Model model) {
        if (personManager.deletePerson(id)) {
            model.addAttribute("successMessage", "Operation succesful");
            //  return "success";
        } else {
            model.addAttribute("errorMessage", "Operation unsuccesful");
//    return "error";
        }
        return "redirect:/person";
    }


}