package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.service.AddressManager;

import java.util.List;

@RestController
public class AddressController {
    private final AddressManager addressManager;


    public AddressController(@Autowired AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    @PostMapping("/api/address")
    Address addAddress(@RequestBody Address address) {
        return addressManager.addAddress(address);
    }

    @PutMapping("/api/address")
    Address modifyAddress(@RequestBody Address address) {
        Address d = addressManager.printAddress(address.getId());
        if (d == null) {
            throw new AddressNotFoundException();
        }
        return addressManager.editAddress(address);
    }

    @GetMapping("/api/address")
    List<Address> getAll() {
        return addressManager.printAllAddresses();
    }

    @GetMapping("/api/address/{id}")
    Address findAddressByName(@PathVariable("id") Long id) {
        Address d = addressManager.printAddress(id);
        if (d == null) {
            throw new AddressNotFoundException();
        }

        return d;
    }

    @DeleteMapping("/api/address/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        addressManager.deleteAddress(id);
    }


}