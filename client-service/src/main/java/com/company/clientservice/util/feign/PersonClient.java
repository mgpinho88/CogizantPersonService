package com.company.clientservice.util.feign;

import com.company.clientservice.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "person-service")
public interface PersonClient {

    @RequestMapping(value = "/person/addPerson", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody @Valid Person person);


    @RequestMapping(value = "/person/name", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getPepleByName(@PathVariable("name") String name);

}
