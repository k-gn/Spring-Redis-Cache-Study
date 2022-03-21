package me.study.cache.service;

import me.study.cache.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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


    @Test
    void test() {

        Person insertPerson = personService.insertPerson(this.person);
        Person findPersonOne = personService.findPerson(insertPerson.getId());
        System.out.println(findPersonOne);
        Person findPersonTwo = personService.findPerson(insertPerson.getId());
        System.out.println(findPersonTwo);
    }
}