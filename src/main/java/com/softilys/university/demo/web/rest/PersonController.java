package com.softilys.university.demo.web.rest;

import com.softilys.university.demo.model.Person;
import com.softilys.university.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/persons/{email}")
    public ResponseEntity<Person> getPerson(@PathVariable(name="email") String email) {
        return ResponseEntity.ok(personService.getPersonDetails(email)) ;
    }

}
