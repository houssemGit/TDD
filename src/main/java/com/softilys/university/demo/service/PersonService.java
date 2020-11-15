package com.softilys.university.demo.service;

import com.softilys.university.demo.Repository.PersonRepository;
import com.softilys.university.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PersonService implements PersonServiceImpl {
    @Autowired
    PersonRepository personRepository;
    public Person getPersonDetails(String email) {
        return personRepository.getOne(email) ;
    }

    public List<Person> getPersonsDetails() { return personRepository.findAll() ;}

    @Override
    public String deletePersonById(String email) {

        Optional<Person> employee = personRepository.findById(email);
        System.out.println(employee);
        if(employee.isPresent())
        {
            personRepository.deleteById(email);
            return "personne supprimé";
        } else {
            return "personne n' existe pas ";
        }
    }

    @Override
    public Person addPerson(Person p) {
        return null;
    }

    @Override
    public Person modifyPerson(Person p) {
        return null;
    }
}
