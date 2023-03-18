package pl.edu.ug.jluc.ap.districts.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Collection;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
public class District {
    private Long id;
    private String district;
    private String numberOfResidents;
    private String area;
    private String populationDensity;
    private Collection<Address> addresses;// = new HashSet<>();

    public District(){}
    public District(String district){
        this.district = district;
    }
    public District(String district, String numberOfResidents, String area, String populationDensity){
        this.district = district;
        this.numberOfResidents = numberOfResidents;
        this.area = area;
        this.populationDensity = populationDensity;
    }
    public District(Long id, String district, String numberOfResidents, String area, String populationDensity){
        this.id = id;
        this.district = district;
        this.numberOfResidents = numberOfResidents;
        this.area = area;
        this.populationDensity = populationDensity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getNumberOfResidents() { return numberOfResidents; }
    public void setNumberOfResidents(String numberOfResidents) { this.numberOfResidents = numberOfResidents; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPopulationDensity() { return populationDensity; }
    public void setPopulationDensity(String populationDensity) { this.populationDensity = populationDensity; }

//    @OneToMany()
    //fetch = FetchType.LAZY - default type   // EAGER          PERSIST /  ALL
    @OneToMany(mappedBy="district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  @Fetch(FetchMode.JOIN)
//    @JsonManagedReference
//    @JsonBackReference
    public Collection<Address> getAddresses() {
        return addresses;
    }
    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

//    public String toString(){
////        return this.district+":[numberOfResidents: "+this.numberOfResidents+", area: "+this.area+ ", populationDensity: " + this.populationDensity + "]";
//
//
//    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", numberOfResidents='" + numberOfResidents + '\'' +
                ", area='" + area + '\'' +
                ", populationDensity='" + populationDensity + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}