package pl.edu.ug.jluc.ap.districts.other;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.edu.ug.jluc.ap.districts.service.PersonService;
import pl.edu.ug.jluc.ap.districts.domain.Person;
import pl.edu.ug.jluc.ap.districts.service.PersonService;
import reactor.core.publisher.Mono;

@Component
public class RequestHandler {

    private final PersonService personService;

    public RequestHandler(PersonService personService) {
        this.personService = personService;
    }


    public Mono<ServerResponse> personStream(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(personService.personStream(), Person.class);

    }
}
