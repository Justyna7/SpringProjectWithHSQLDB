package pl.edu.ug.jluc.ap.districts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.ug.jluc.ap.districts.service.AddressService;
import pl.edu.ug.jluc.ap.districts.service.DistrictService;
import pl.edu.ug.jluc.ap.districts.service.PersonService;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
public class DistrictsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistrictsApplication.class, args);
	}
	@Bean
	public CommandLineRunner setUpApp(DistrictService districtService, AddressService addressService, PersonService personService) {
		return (args) -> {
			districtService.learning();
			addressService.learning();
			personService.learning();

		};
	}
}
