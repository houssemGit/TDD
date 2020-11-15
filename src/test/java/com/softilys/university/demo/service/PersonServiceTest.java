package com.softilys.university.demo.service;

import com.softilys.university.demo.Repository.PersonRepository;
import com.softilys.university.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @Test
    void getPerson_returnPersonDetails() throws Exception{
        when(personRepository.getOne(anyString()))
                .thenReturn(new Person("houssem.eleuch@softylis.tn","Eleuch","Houssem"));
        assertThat(personService.getPersonDetails(anyString()))
                .isEqualToComparingFieldByField(personRepository.getOne(anyString()));

    }

    @Test
    void getAllPersons_returnAllPersonsDetails(){
        when(personRepository.findAll())
                .thenReturn(Stream.of(new Person("houssem.eleuch@softylis.tn", "Eleuch", "Houssem"), new Person("fakhri.amri@softylis.tn", "amri", "fakhri"))
                        .collect(Collectors.toList()));
        assertThat(personService.getPersonsDetails())
                .isNotEmpty()
                .hasSize(2)
                .contains(new Person("fakhri.amri@softylis.tn", "amri", "fakhri"));
    }
    @Rollback(true)
    @Test
    void deletePerson_returnDeletedPerson(){
        assertThat(personService.deletePersonById("houssem.eleuch@softylis.tn")).isEqualTo("personne supprimé");
    }
}
