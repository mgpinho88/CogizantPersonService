package com.company.personservice.model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private Integer personId;
    private String name;
    private Integer age;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId.equals(person.personId) &&
                name.equals(person.name) &&
                age.equals(person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name, age);
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
