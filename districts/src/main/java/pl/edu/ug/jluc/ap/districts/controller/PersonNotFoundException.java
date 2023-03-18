package pl.edu.ug.jluc.ap.districts.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Person in DB")
public class PersonNotFoundException extends RuntimeException {

}
