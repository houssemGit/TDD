package com.softilys.university.demo.service;

import com.softilys.university.demo.Repository.PersonRepository;
import com.softilys.university.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PersonService implements PersonServiceImpl {
    @Autowired
    PersonRepository personRepository;
    public Person getPersonDetails(String email) {
        return personRepository.getOne(email) ;
    }
}
