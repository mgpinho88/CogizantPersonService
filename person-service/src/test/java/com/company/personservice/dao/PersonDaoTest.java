package com.company.personservice.dao;

import com.company.personservice.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonDaoTest {

        @Autowired
        PersonDao dao;

        @Before
        public void setUp() {

            List<Person> people = dao.getAllPeople();

            people.stream().forEach(person -> dao.deletePerson(person.getPersonId()));
        }

        @Test
        public void testAddPerson() {
            Person person = new Person();

            person.setName("Mark");
            person.setAge(30);

            person = dao.addPerson(person);

            Person fromDatabase = dao.getPerson(person.getPersonId());

            assertEquals(person, fromDatabase);

            dao.deletePerson(person.getPersonId());

            fromDatabase = dao.getPerson(person.getPersonId());

            assertNull(fromDatabase);

        }

        @Test
        public void testGetPeopleByName() {

            Person mark = new Person();
            Person paul = new Person();

            mark.setName("Mark");
            mark.setAge(30);

            paul.setName("Paul");
            paul.setAge(31);

            mark = dao.addPerson(mark);
            paul = dao.addPerson(paul);

            List<Person> personList = dao.getPeopleByName("mark");

            assertEquals(1, personList.size());
        }
}
