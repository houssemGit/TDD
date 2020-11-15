package com.softilys.university.demo.web.rest;

import com.softilys.university.demo.model.Person;
import com.softilys.university.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/persons/{email}")
    public ResponseEntity<Person> getPerson(@PathVariable(name="email") String email) {
        return ResponseEntity.ok(personService.getPersonDetails(email)) ;
    }
    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personService.getPersonsDetails()) ;
    }

    @DeleteMapping(value = "/persons/{email}")
    public ResponseEntity<HttpStatus> deletePerson (@PathVariable("email") String email)
    {
        try {
            personService.deletePersonById(email);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/persons")
    public  ResponseEntity<Person> addPerson (@RequestBody Person p){
        try {
            Person person = personService.addPerson(p);
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/persons/{email}")
    public  ResponseEntity<Person> addPerson (@PathVariable("email") String email,@RequestBody Person p){
        try {
            Person person = personService.modifyPerson(p);
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
