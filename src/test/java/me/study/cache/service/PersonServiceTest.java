package me.study.cache.service;

import me.study.cache.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    public Person person = Person.builder()
            .name("gyul")
            .age(26)
            .createdAt(LocalDateTime.now())
            .build();

    public Person modPerson = Person.builder()
            .name("sso")
            .age(23)
            .createdAt(LocalDateTime.now())
            .build();

    @Test
    void searchTest() {
        Person insertPerson = personService.insertPerson(this.person);
        Person findPersonOne = personService.findPerson(insertPerson.getId());
        assertEquals("gyul", findPersonOne.getName());

        Person findPersonTwo = personService.findPerson(insertPerson.getId());
        assertEquals("gyul", findPersonOne.getName());
    }

    @Test
    void modifyTest() {
        Person insertPerson = personService.insertPerson(this.person);
        Person findPersonOne = personService.findPerson(insertPerson.getId());
        assertEquals("gyul", findPersonOne.getName());

        personService.modifyPerson(insertPerson.getId(), modPerson);
        Person findPersonTwo = personService.findPerson(insertPerson.getId());
        assertEquals("sso", findPersonTwo.getName());
    }

    @Test
    void deleteTest() {
        Person insertPerson = personService.insertPerson(this.person);
        Person findPersonOne = personService.findPerson(insertPerson.getId());
        assertEquals("gyul", findPersonOne.getName());

        personService.deletePerson(insertPerson.getId());
        assertThrows(NoSuchElementException.class, () -> {
            personService.findPerson(insertPerson.getId());
        });
    }
}