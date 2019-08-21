package com.company.clientservice.controller;

import com.company.clientservice.model.Person;
import com.company.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientService service;

    @RequestMapping(value = "/clientfe/addperson",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody @Valid Person person){

        return service.createPerson(person);
    }

    @RequestMapping(value = "/clientfe/person/{name}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getPersonByName(@PathVariable("name") String name){
        return service.getPersonsByName(name);

    }

}
