package com.softilys.university.demo.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softilys.university.demo.model.Person;
import com.softilys.university.demo.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class) //désactivera la configuraton automatque complète et n'appliquera à la place que la configuraton relative aux tests MVC
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean //pour ajouter des objets mocks au Context Spring Applicaton.
    private PersonService personService;


    @Test
    public void getPerson_returnPersonDetails() throws Exception{
       given(personService.getPersonDetails(anyString())).willReturn(new Person("houssem.eleuch@softylis.tn","Eleuch","Houssem"));
       mockMvc.perform(MockMvcRequestBuilders.get("/persons/houssem.eleuch@softylis.tn")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk()).andDo(print())
               .andExpect(jsonPath("firstName").value("Houssem"))
               .andExpect(jsonPath("lastName").value("Eleuch"))
               .andExpect(jsonPath("email").value("houssem.eleuch@softylis.tn"));
    }

    @Test
    void getAllPersons_returnAllPersonsDetails() throws Exception{
        given(personService.getPersonsDetails())
                .willReturn(Stream.of(new Person("houssem.eleuch@softylis.tn", "Eleuch", "Houssem"), new Person("fakhri.amri@softylis.tn", "amri", "fakhri"))
                .collect(Collectors.toList()));
        mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("fakhri"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("houssem.eleuch@softylis.tn"));

    }

    @Test
    void deletePerson_returnDeletedPerson()throws Exception{
        given(personService.deletePersonById(anyString()))
                .willReturn("personne supprimé");
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/fakhri.amri@softylis.tn"))
                .andExpect(status().isAccepted()).andDo(print());
    }
    @Test
    void addPerson_returnaddedPerson()throws Exception{

        Person p = new Person("fares.eleuch@softylis.tn", "eleuch", "fares");
        given(personService.addPerson(new Person()))
                .willReturn(p);

        System.out.println(asJsonString(p));

        mockMvc.perform( MockMvcRequestBuilders
                .post("/persons")
                .content(asJsonString(p))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$email").value("fares.eleuch@softylis.tn"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("eleuch"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("fares"));


    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void modifyPerson_returnModifiedPerson()throws Exception{
        Person p = new Person("fares.eleuch@softylis.tn", "eleuch", "fares");

        mockMvc.perform( MockMvcRequestBuilders
                .put("/persons/{email}", "fares.eleuch@softylis.tn")
                .content(asJsonString(p))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("fares"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("eleuch"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("fares.eleuch@softylis.tn"));

    }
}
