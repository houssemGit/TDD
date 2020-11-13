package com.softilys.university.demo.service;

import com.softilys.university.demo.Repository.PersonRepository;
import com.softilys.university.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
}
