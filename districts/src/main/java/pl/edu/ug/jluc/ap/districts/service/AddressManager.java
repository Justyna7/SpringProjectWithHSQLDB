package pl.edu.ug.jluc.ap.districts.service;

import pl.edu.ug.jluc.ap.districts.domain.Address;

import java.util.List;

public interface AddressManager {
    Address addAddress(Address address);
    Address editAddress(Address address);
    List<Address> printAllAddresses();
    boolean deleteAddress(Long id);
    Address printAddress(Long id);
}
