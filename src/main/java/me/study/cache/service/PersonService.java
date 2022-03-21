package me.study.cache.service;

import lombok.RequiredArgsConstructor;
import me.study.cache.entity.Person;
import me.study.cache.repository.PersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Cacheable(value = "person", key = "#id", unless = "#result == null")
    public Person findPerson(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Person insertPerson(Person person) {
        return personRepository.save(person);
    }

    @CachePut(value = "person", key = "#id")
    @Transactional
    public Person modifyPerson(Long id, Person person) {
        Person dbPerson = personRepository.findById(id).orElseThrow();
        dbPerson.setAge(person.getAge());
        dbPerson.setName(person.getName());
        return dbPerson;
    }

    @CacheEvict(value = "person", key = "#id")
    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
