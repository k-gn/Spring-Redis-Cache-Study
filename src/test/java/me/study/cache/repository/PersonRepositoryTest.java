package me.study.cache.repository;

import me.study.cache.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void test() {
        Person person = new Person("park", 20);

        Person dbPerson = personRepository.save(person);
        Optional<Person> personResult = personRepository.findById(dbPerson.getId());

        System.out.println(personResult);
        System.out.println(personRepository.count());
        personRepository.delete(dbPerson);
    }
}