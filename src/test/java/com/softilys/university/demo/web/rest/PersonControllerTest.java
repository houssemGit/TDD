package com.softilys.university.demo.web.rest;

import com.softilys.university.demo.model.Person;
import com.softilys.university.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
}
