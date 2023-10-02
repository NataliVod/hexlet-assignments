package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    Person create(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        this.personRepository.deleteById(id);
    }

    // END
}
