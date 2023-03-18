package pl.edu.ug.jluc.ap.districts.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Address {
    private Long id;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String zipCode;
    private String locality;
    private District district;
    public Collection<Person> people;
    public Address(){};
    public Address(String street, String houseNumber, String apartmentNumber, String zipCode, String locality){
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
        this.locality = locality;
    }
    public Address(Long id, String street, String houseNumber, String apartmentNumber, String zipCode, String locality){
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
        this.locality = locality;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getApartmentNumber() { return apartmentNumber; }
    public void setApartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }

    @ManyToOne
//    @JsonBackReference
//    @JsonManagedReference
    public District getDistrict(){
        return district;
    }
    public void setDistrict(District district){
        this.district = district;
    }

//    @ManyToMany(mappedBy="addresses")
//fetch = FetchType.LAZY - default type  // EAGER // PERSIST
    @ManyToMany(mappedBy="addresses", cascade = CascadeType.ALL, fetch = FetchType.LAZY) @Fetch(FetchMode.JOIN)
//    @JsonManagedReference
    public Collection<Person> getPeople() {
        return people;
    }
    public void setPeople(Collection<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", locality='" + locality + '\'' +
                ", dzielnica=" + district.getDistrict() +
                ", people=" + people +
                '}';
    }


}
