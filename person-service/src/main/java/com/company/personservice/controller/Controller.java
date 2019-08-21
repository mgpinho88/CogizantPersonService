package com.company.personservice.controller;

import com.company.personservice.dao.PersonDao;
import com.company.personservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class Controller {

    @Autowired
    PersonDao dao;

    @RequestMapping(value = "/person/addperson", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return dao.addPerson(person);
    }

    @RequestMapping(value = "/person/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getPepleByName(@PathVariable("name") String name) {
        return dao.getPeopleByName(name);
    }
}
