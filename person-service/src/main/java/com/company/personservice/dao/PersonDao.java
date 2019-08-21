package com.company.personservice.dao;

import com.company.personservice.model.Person;

import java.util.List;

public interface PersonDao {

    public Person addPerson(Person person);

    public Person getPerson(Integer personId);

    public List<Person> getAllPeople();

    public List<Person> getPeopleByName(String name);

    public void deletePerson(int id);


}
