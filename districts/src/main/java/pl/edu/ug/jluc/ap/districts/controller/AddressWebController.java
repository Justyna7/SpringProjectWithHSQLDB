package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.service.AddressManager;

@Controller
public class AddressWebController {
    private final AddressManager addressManager;

    public AddressWebController(@Autowired AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    @GetMapping("/address")
    public String allAddresses(Model model) {
        model.addAttribute("allAddressssesFromDB", addressManager.printAllAddresses());
        return "address-all";
    }

    @GetMapping("/address/form")
    public String editAddress (Model model) {
        model.addAttribute("addressToAdd", new Address("unknown", "unknown","unknown","unknown","unknown"));
        return "address-edit";
    }

    @PostMapping("/address")
    public String addNewAddress(@ModelAttribute Address addressToAdd, Model model) {
        addressManager.addAddress(addressToAdd);
        model.addAttribute("allAddressssesFromDB", addressManager.printAllAddresses());
        return "address-add";
    }

    @GetMapping("/address/delete/{name}")
    public String deleteAddress (@PathVariable("name") Long id, Model model) {
        if (addressManager.deleteAddress(id)) {
            model.addAttribute("successMessage", "Operation succesful");
            //  return "success";
        } else {
            model.addAttribute("errorMessage", "Operation unsuccesful");
//    return "error";
        }
        return "redirect:/address";
    }


}