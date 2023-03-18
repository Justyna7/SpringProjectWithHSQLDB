package pl.edu.ug.jluc.ap.districts.service;

import org.springframework.stereotype.Service;
import pl.edu.ug.jluc.ap.districts.domain.Address;
import pl.edu.ug.jluc.ap.districts.repository.AddressRepository;
import pl.edu.ug.jluc.ap.districts.repository.DistrictRepository;

import java.util.List;

@Service
public class AddressManagerInMemory implements AddressManager{

    final DistrictRepository districtRepository;
    final AddressRepository addressRepository;
    public AddressManagerInMemory(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addAddress(Address address) {
        Address addressDoDodania = new Address(
                address.getStreet(),
                address.getHouseNumber(),
                address.getApartmentNumber(),
                address.getZipCode(),
                address.getLocality());
        addressRepository.save(addressDoDodania);
        return addressDoDodania;
    }

    @Override
    public Address editAddress(Address address) {
        addressRepository.save(address);
        return address;
    }

    @Override
    public List<Address> printAllAddresses() {
        addressRepository.getAllAddresss().forEach(System.out::println);
        return addressRepository.getAllAddresss();
    }

    @Override
    public boolean deleteAddress (Long id) {

        Address address = addressRepository.findById(id).orElse(null);
        if (address == null){
            return false;
        }
        addressRepository.deleteById(id);
        return true;
    }

    @Override
    public Address printAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
}