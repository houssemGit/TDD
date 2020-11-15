package com.softilys.university.demo.service;

import com.softilys.university.demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonServiceImpl {
    Person getPersonDetails(String anyString);
    List<Person> getPersonsDetails();
    String deletePersonById(String email);
    Person addPerson(Person p);
    Person modifyPerson(Person p);
}
