package pl.edu.ug.jluc.ap.districts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Address in DB")
public class AddressNotFoundException extends RuntimeException {

}