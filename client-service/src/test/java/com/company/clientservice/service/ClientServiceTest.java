package com.company.clientservice.service;

import com.company.clientservice.controller.ClientController;
import com.company.clientservice.model.Person;
import com.company.clientservice.util.feign.PersonClient;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ClientServiceTest {

    ClientService service;
    PersonClient client;


    @Before
    public void setUp() throws Exception {
        setUpPersonClientMock();
        service = new ClientService(client);
    }

    public void setUpPersonClientMock(){
        client = mock(PersonClient.class);

        Person person = new Person();
        person.setName("Mark");
        person.setAge(30);

        Person fromClient = new Person();
        fromClient.setPersonId(1);
        fromClient.setName("Mark");
        fromClient.setAge(30);

        List<Person> people = new ArrayList<>();
        people.add(fromClient);

        doReturn(fromClient).when(client).createPerson(person);
        doReturn(people).when(client).getPepleByName("Mark");
    }


    @Test
    public void createGetPerson() {
        Person person = new Person();

        person.setName("Mark");
        person.setAge(30);
        person = service.createPerson(person);

        List<Person> fromService = service.getPersonsByName("Mark");

        Person clientPerson = fromService.get(0);

        assertEquals(person,clientPerson);
        assertEquals(1, fromService.size());

    }

}