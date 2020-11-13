package com.softilys.university.demo;

import com.softilys.university.demo.BenifitClaim2Application;
import com.softilys.university.demo.model.Person;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;





@SpringBootTest(classes = BenifitClaim2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonRessourceIntTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getPerson_returnPersonDetails(){

        ResponseEntity <Person> response = testRestTemplate.getForEntity("/persons/houssem.eleuch@softylis.tn", Person.class) ;
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getEmail()).isEqualTo("houssem.eleuch@softylis.tn");
        Assertions.assertThat(response.getBody().getLastName()).isEqualTo("eleuch");
        Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("houssem");


    }
}
