package me.study.cache.service;

import lombok.RequiredArgsConstructor;
import me.study.cache.entity.Person;
import me.study.cache.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findPersonAll() {
        return personRepository.findAll();
    }

    public Person findPerson(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public void insertPerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
