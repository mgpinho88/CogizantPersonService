package com.company.personservice.dao;

import com.company.personservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDaoJdbcTemplateImple implements PersonDao{

    JdbcTemplate jdbc;

    @Autowired
    public PersonDaoJdbcTemplateImple(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String CREATE_PERSON = "insert into person (name, age) values (?, ?)";
    private static final String GET_PERSON = "select * from person where person_id =?";
    private static final String GET_ALL_PEOPLE = "select * from person";
    private static final String GET_BY_NAME = "select * from person where name = ?";
    private static final String DELETE_PERSON = "delete from person where person_id = ?";

    @Override
    @Transactional
    public Person addPerson(Person person) {

        jdbc.update(
                CREATE_PERSON,
                person.getName(),
                person.getAge()
        );

        int personId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);

        person.setPersonId(personId);

        return person;
    }

    @Override
    public Person getPerson(Integer personId) {

        try {
            return jdbc.queryForObject(GET_PERSON, this::mapRowToPerson, personId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Person> getAllPeople() {
        return jdbc.query(GET_ALL_PEOPLE, this::mapRowToPerson);
    }

    @Override
    public List<Person> getPeopleByName(String name) {
        return jdbc.query(GET_BY_NAME, this::mapRowToPerson, name);
    }

    @Override
    public void deletePerson(int id) {
        jdbc.update(DELETE_PERSON, id);
    }

    private Person mapRowToPerson(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setPersonId(rs.getInt("person_id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));

        return person;
    }
}

