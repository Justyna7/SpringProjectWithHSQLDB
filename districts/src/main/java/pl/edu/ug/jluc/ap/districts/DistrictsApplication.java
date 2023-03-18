package pl.edu.ug.jluc.ap.districts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.service.AddressService;
import pl.edu.ug.jluc.ap.districts.service.DistrictService;
import pl.edu.ug.jluc.ap.districts.service.PersonService;
import org.springframework.boot.ApplicationRunner;
import reactor.core.publisher.Flux;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Duration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DistrictsApplication {

	Logger logger = LoggerFactory.getLogger(DistrictsApplication.class);

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
		@Bean
	ApplicationRunner runner() {
		return args -> {
			WebClient.create("http://localhost:8080/").get()
					.uri("personStream")
					.retrieve()
					.bodyToFlux(Person.class)
					.subscribe(data -> logger.info(data.toString()));
		};
	}
	static Person setSurname(Person p, String s)  {
		p.setSurname(s);
		return p;
	}
	@Bean
	ApplicationRunner learning() {

		return args -> {
			List<Person> osoby = new ArrayList<Person>();
			osoby.add(new Person("Elżbieta", "Nowak", new Date(1972, Calendar.JUNE, 15 )));
			osoby.add(new Person("Krzysztof", "Nowak", new Date(1970, Calendar.APRIL, 3 )));
			osoby.add(new Person("Irena", "Nowakowska", new Date(1967, Calendar.JANUARY, 23 )));
			osoby.add(new Person("Iga", "Wójcik", new Date(1985, Calendar.NOVEMBER, 17 )));
			osoby.add(new Person("Marcin", "Wójcik", new Date(1984, Calendar.JUNE, 13 )));
			osoby.add(new Person("Kamil", "Wójcik", new Date(2005, Calendar.JUNE, 29 )));
			osoby.add(new Person("Magdalena", "Kowalska", new Date(1995, Calendar.FEBRUARY, 25 )));
			osoby.add(new Person("Iwona", "Nowak", new Date(1993, Calendar.DECEMBER, 15 )));

			// Non reactive Java Streams
			osoby.stream()
					.filter(n -> n.getDateOfBirth().getYear() % 2 != 0)
					.map(n -> setSurname(n, "Adamczyk") )
					.forEach(data -> logger.info("Java (non reactive) Stream: " + data.toString()));

			// Reactive Java Streams
			Flux.interval(Duration.ofSeconds(1))
					.take(10)
					.filter(n -> n % 2 == 0)
					.map(n -> n * 10)
					.subscribe(data -> logger.info("Reactive Stream: " + data.toString()));

		};
	}
}
