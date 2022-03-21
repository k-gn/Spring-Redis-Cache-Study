package me.study.cache.repository;

import me.study.cache.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    public Person person = Person.builder()
            .name("gyul")
            .age(26)
            .createdAt(LocalDateTime.now())
            .build();

    @Test
    void test() {
        Person dbPerson = personRepository.save(person);
        Optional<Person> personResult = personRepository.findById(dbPerson.getId());

        System.out.println(personResult);
        System.out.println(personRepository.count());
        personRepository.delete(dbPerson);
    }
}