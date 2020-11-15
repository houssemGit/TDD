package com.softilys.university.demo;

import com.softilys.university.demo.BenifitClaim2Application;
import com.softilys.university.demo.model.Person;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = BenifitClaim2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonRessourceIntTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getPerson_returnPersonDetails(){

        ResponseEntity <Person> response = testRestTemplate.getForEntity("/persons/houssem.eleuch@softylis.tn", Person.class) ;
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo("houssem.eleuch@softylis.tn");
        assertThat(response.getBody().getLastName()).isEqualTo("eleuch");
        assertThat(response.getBody().getFirstName()).isEqualTo("houssem");


    }
    @Test
    void getAllPersons_returnAllPersonsDetails(){
        ResponseEntity <Person[]> response = testRestTemplate.getForEntity("/persons", Person[].class) ;

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .hasSize(2)
                .contains(new Person("fakhri.amri@softylis.tn", "amri", "fakhri"));
    }

    @Disabled
    @Test
    void deletePerson_returnDeletedPerson(){
        final  String  email= "houssem.eleuch@softylis.tn";
        ResponseEntity <Person> response = testRestTemplate.getForEntity("/persons/"+email, Person.class) ;
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo(email);

        testRestTemplate.delete("/persons/"+email);
        response = testRestTemplate.getForEntity("/persons/"+email, Person.class) ;
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
    @Test
    void postPerson_returnPostedPerson(){
        Person person = new Person();
        person.setEmail("fares.eleuch@softylis.tn");
        person.setFirstName("fares");
        person.setLastName("eleuch");
        ResponseEntity<Person> postResponse = testRestTemplate.postForEntity( "/persons", person, Person.class);
        assertThat(postResponse).isNotNull();
        assertThat(postResponse.getBody()).isNotNull();

    }

    @Test
    void putPerson_returnPutedPerson(){
        String email = "houssem.eleuch@softylis.tn";
        Person person = testRestTemplate.getForObject("/employees/" + email, Person.class);
        person.setFirstName("houssem1");
        person.setLastName("eleuch1");
        testRestTemplate.put("/employees/" + email, person);
        Person updatedPerson = testRestTemplate.getForObject("/employees/" + email, Person.class);
        assertNotNull(updatedPerson);
        assertThat(updatedPerson.getEmail()).isEqualTo("houssem1");

    }
}
