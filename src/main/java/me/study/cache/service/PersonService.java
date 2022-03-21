package me.study.cache.service;

import lombok.RequiredArgsConstructor;
import me.study.cache.entity.Person;
import me.study.cache.repository.PersonRepository;
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
}
