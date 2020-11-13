package com.softilys.university.demo.dao;

import com.softilys.university.demo.Repository.PersonRepository;
import com.softilys.university.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

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

}
