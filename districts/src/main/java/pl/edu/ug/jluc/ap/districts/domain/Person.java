package pl.edu.ug.jluc.ap.districts.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.*;

import java.util.Collection;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {
    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private Collection<Address> addresses;
//    @JsonIgnoreProperties("marriage")
    private Person marriage;

    public Person(){};
    public Person(String name, String surname, Date dateOfBirth){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }
    public Person(Long id, String name, String surname, Date dateOfBirth){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    @ManyToMany( fetch = FetchType.EAGER) @Fetch(FetchMode.JOIN)
//    cascade = CascadeType.ALL,
//    @JsonBackReference
    public Collection<Address> getAddresses(){
        return addresses;
    }
    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
//        if (addresses != null) {
//            for (Iterator<Adres> iterator = addresses.iterator(); iterator.hasNext(); ) {
//                //System.out.println("value= " + iterator.next());
//            }
//        }
//        System.out.println(addresses);
    }
    @OneToOne
    //@JsonManagedReference
    //@JsonBackReference
    public Person getMarriage() {
        return marriage;
    }

    public void setMarriage(Person marriage) {
        this.marriage = marriage;
    }

    @Override
    public String toString() {
        String m = null;
        if (marriage != null){
            m = marriage.toStringSimple();
        }
        return "Osoba{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", addresses=" + "addresses" +
                ", marriage=" + m +
                '}';
    }
    public String toStringSimple() {
        return "Osoba{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", addresses=" + addresses +
                '}';
    }
}
