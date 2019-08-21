package com.company.clientservice.service;

import com.company.clientservice.model.Person;
import com.company.clientservice.util.feign.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientService {


    PersonClient client;

    @Autowired
    public ClientService(PersonClient client){
        this.client = client;
    }

    public Person createPerson(Person person){
        return client.createPerson(person);

    }


    public List<Person> getPersonsByName(String name){
        return client.getPepleByName(name);

    }

}
