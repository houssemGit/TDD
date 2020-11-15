package com.softilys.university.demo.dao;

import com.softilys.university.demo.Repository.PersonRepository;
import com.softilys.university.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void getPerson_returnPersonDetails() throws Exception {
        Person person = personRepository.getOne("houssem.eleuch@softylis.tn");
        assertThat(person.getEmail()).isEqualTo("houssem.eleuch@softylis.tn");
        assertThat(person.getFirstName()).isEqualTo("houssem");
        assertThat(person.getLastName()).isEqualTo("eleuch");

    }

    @Test
    void getAllPersons_returnAllPersonsDetails(){
        List<Person> persons = personRepository.findAll();
        assertThat(persons)
                .hasSize(2)
                .contains(new Person("fakhri.amri@softylis.tn", "amri", "fakhri"));

    }

    @Rollback(true)
    @Test
    void deletePerson_returnDeletedPerson(){
        Optional<Person> p = personRepository.findById("fakhri.amri@softylis.tn");
        if(p.isPresent())
            personRepository.deleteById("fakhri.amri@softylis.tn");
        System.out.println(personRepository.findById("fakhri.amri@softylis.tn"));
        assertThat(personRepository.findById("fakhri.amri@softylis.tn")).isEmpty();
    }
    @Test
    void addPerson_returnAddedPerson(){
        Person p = new Person("fakhri.amri@softylis.tn", "amri", "fakhri");
        Optional<Person> pOP = personRepository.findById("fakhri.amri@softylis.tn");
        if(!pOP.isPresent()) {
            personRepository.save(p);
            assertThat(personRepository.getOne("fakhri.amri@softylis.tn")).isEqualTo(p);
        }
        else assertThat(personRepository.getOne("fakhri.amri@softylis.tn")).isNotNull();

    }
    @Test
    void modifyPerson_returnModifiedPerson(){
        Person p = new Person("fakhri.amri@softylis.tn", "amri", "fakhri");


    }


}
