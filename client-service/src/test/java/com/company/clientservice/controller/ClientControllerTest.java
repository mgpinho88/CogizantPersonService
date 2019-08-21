package com.company.clientservice.controller;

import com.company.clientservice.model.Person;
import com.company.clientservice.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createPerson() throws Exception{
        Person person = new Person();
        person.setName("Mark");
        person.setAge(30);
        //Object to JSON in String
        String inputJson = mapper.writeValueAsString(person);

        Person person1 = new Person();
        person1.setName("Mark");
        person1.setAge(30);



        when(service.createPerson(person)).thenReturn(person1);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rsvp")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(person1)));
    }

//    @Test
//    public void getPersonsByName() {
//        String outputJson = mapper.writeValueAsString(rsvp);
//        when(client.getRsvp(1)).thenReturn(rsvp);
//        this.mockMvc.perform(get("/rsvp/1")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content()
//                        //Below - use the objectmapper output with the json method
//                        .json(outputJson));
//    }

//    }
}